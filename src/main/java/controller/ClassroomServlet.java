package controller;


import modelDto.ClassroomDto;
import org.apache.log4j.Logger;
import repository.ClassroomRepository;
import service.ClassroomService;
//import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "ClassroomServlet", urlPatterns = "/classroom")
public class ClassroomServlet extends HttpServlet {

//    @Inject
//    private ClassroomDto classroomDto;
//    @Inject
//    private Validator validator;

   private static Logger logger = Logger.getLogger(ClassroomServlet.class.getName());
   private ClassroomService classroomService = new ClassroomService(new ClassroomRepository());

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
                          throws ServletException, IOException {
        logger.info("Inside method doPost");
        String action = request.getParameter("actionClassroom");
            switch (action) {
                case "add":
                    logger.info("Before method addClassroomAction");
                    addClassroomAction(request, response);
                    break;
                case "edit":
                    logger.info("Before method editClassroomAction");
                    editClassroomAction(request, response);
                    break;
                case "remove":
                    logger.info("Before method removeClassroomAction");
                    removeClassroomAction(request, response);
                    break;
            }
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException {
        logger.info("Inside method goGet");
        String action = request.getParameter("searchAction");
            if (action != null) {
                switch (action) {
                    case "searchById":
                        logger.info("Before method searchClassroomById");
                        searchClassroomById(request, response);
                        break;
                }
            }else {
                List<ClassroomDto> result = classroomService.getClassroomsDto();
                forwardListClassrooms(request, response, result);
            }
        }

    private void searchClassroomById(HttpServletRequest request, HttpServletResponse response)
                                     throws ServletException, IOException {
        logger.info("Inside method searchClassroomById");
        try {
            long idClassroom = Long.valueOf(request.getParameter("idClassroom"));
            ClassroomDto classroomDto = classroomService.classroomDtoById(idClassroom);
            request.setAttribute("classroomDto", classroomDto);
            request.setAttribute("actionClassroom", "edit");
            String nextJSP = "/classroom.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("Error", "Classroom doesn't found");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Classroom doesn't found", ex);
            }
        }

    private void forwardListClassrooms(HttpServletRequest request, HttpServletResponse response, List classroomDtoList)
                                       throws ServletException, IOException {
        logger.info("Inside method forwardListClassrooms");
        String nextJSP = "/classroom_list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("classroomDtoList", classroomDtoList);
        dispatcher.forward(request, response);
        }

    private void addClassroomAction(HttpServletRequest request, HttpServletResponse response)
                                    throws ServletException, IOException {
        logger.info("Inside method addClassroomAction");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        String classroomNumber = request.getParameter("classroomNumber");
        String typeRoom = request.getParameter("typeRoom");
        ClassroomDto classroomDto = new ClassroomDto(classroomNumber, typeRoom);
        Set<ConstraintViolation<ClassroomDto>> violations = validator.validate(classroomDto);
        if (violations.size() > 0) {
            for (ConstraintViolation<ClassroomDto> violation : violations) {

                String message = violation.getMessage();
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            }
        }else {
            try {
                long idClassroom = classroomService.addClassroomDto(classroomDto);
                List<ClassroomDto> classroomDtoList = classroomService.getClassroomsDto();
                request.setAttribute("idClassroomDto", idClassroom);
                String message = "The new classroom has been successfully created";
                request.setAttribute("message", message);
                forwardListClassrooms(request, response, classroomDtoList);
            } catch (Exception e) {
                String message = "The classroom doesn't created";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
                logger.error("Classroom doesn't created", e);
            }
        }
   }

    private void editClassroomAction(HttpServletRequest request, HttpServletResponse response)
                                     throws ServletException, IOException {
        logger.info("Inside method editClassroomAction");
        try {
            String classroomNumber = request.getParameter("classroomNumber");
            String typeRoom = request.getParameter("typeRoom");
            long idClassroom = Long.valueOf(request.getParameter("idClassroom"));
            ClassroomDto classroomDto = new ClassroomDto();
            classroomDto.setClassroomNumber(classroomNumber);
            classroomDto.setTypeRoom(typeRoom);
            classroomDto.setId(idClassroom);
            boolean success = classroomService.updateClassroomDto(classroomDto);
            if (success) {
                String message = "The classroom has been successfully updated.";
                List<ClassroomDto> classroomDtoList = classroomService.getClassroomsDto();
                request.setAttribute("idClassroom", idClassroom);
                request.setAttribute("message", message);
                forwardListClassrooms(request, response, classroomDtoList);
            }
        }catch(Exception e) {
            String message = "Classroom doesn't updated";
            request.setAttribute("message", message );
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Classroom doesn't updated", e);
        }
    }

    private void removeClassroomAction(HttpServletRequest req, HttpServletResponse resp)
                                     throws ServletException, IOException {
        logger.info("Inside method removeClassroomById");
        try {
            long idClassroom = Long.valueOf(req.getParameter("idClassroom"));
            boolean confirm = classroomService.deleteClassroomDtoById(idClassroom);
                if (confirm) {
                    String message = "The classroom has been successfully removed.";
                    req.setAttribute("message", message);
                    List<ClassroomDto> classroomDtoList = classroomService.getClassroomsDto();
                    forwardListClassrooms(req, resp, classroomDtoList);
                }
        }catch (Exception e) {
            String message = "Classroom doesn't removed";
            req.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
            logger.error("Classroom doesn't removed", e);
            }
        }
    }

