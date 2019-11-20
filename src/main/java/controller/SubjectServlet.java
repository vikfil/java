package controller;

import model.Subject;
import modelDto.ClassroomDto;
import modelDto.SubjectDto;
import org.apache.log4j.Logger;
import repository.SubjectRepository;
import service.SubjectService;
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

@WebServlet(name = "SubjectServlet", urlPatterns = "/subject")
public class SubjectServlet extends HttpServlet {
   private static Logger logger = Logger.getLogger(SubjectServlet.class.getName());
   private SubjectService subjectService = new SubjectService(new SubjectRepository());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inside method doPost");
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                logger.info("Before method addSubjectAction");
               addSubjectAction(request, response);
               break;
            case "edit":
                logger.info("Before method editSubjectAction");
                editSubjectAction(request, response);
                break;
            case "remove":
                logger.info("Before method removeSubjectAction");
                removeSubjectAction(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inside method doGet");
        String action = request.getParameter("searchAction");
        if (action != null) {
           switch (action) {
              case "searchById":
                  logger.info("Before method searchSubjectById");
                  searchSubjectById(request, response);
                  break;
           }
        }else {
        List<SubjectDto> result = subjectService.getSubjectsDto();
        forwardListSubjects(request, response, result);
        }
    }

    private void searchSubjectById(HttpServletRequest request, HttpServletResponse response)
                                  throws ServletException, IOException {
        logger.info("Inside method searchSubjectById");
        try {
            long idSubject = Long.valueOf(request.getParameter("idSubject"));
            SubjectDto subjectDto = subjectService.subjectDtoById(idSubject);
            request.setAttribute("subjectDto", subjectDto);
            request.setAttribute("action", "edit");
            String nextJSP = "/subject.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("Error", "Subject doesn't found");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Subject doesn't found", ex);
        }
    }

    private void forwardListSubjects(HttpServletRequest request, HttpServletResponse response, List subjectDtoList)
                                     throws ServletException, IOException {
        logger.info("Inside method forwardListSubjects");
        String nextJSP = "/subject_list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("subjectDtoList", subjectDtoList);
        dispatcher.forward(request, response);
        }

    private void addSubjectAction(HttpServletRequest request, HttpServletResponse response)
                                  throws ServletException, IOException {
        logger.info("Inside method addSubjectAction");

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            String name = request.getParameter("name");
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setSubjectName(name);
            Set<ConstraintViolation<SubjectDto>> violations = validator.validate(subjectDto);
            if (violations.size() > 0) {
                for (ConstraintViolation<SubjectDto> violation : violations) {
                    String message = violation.getMessage();
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
                }
            }else {
                try {
                    long idSubject = subjectService.addSubjectDto(subjectDto);
                    List<SubjectDto> subjectDtoList = subjectService.getSubjectsDto();
                    request.setAttribute("idSubject", idSubject);
                    String message = "The new subject has been successfully created";
                    request.setAttribute("message", message);
                    forwardListSubjects(request, response, subjectDtoList);
                } catch (Exception e) {
                    String message = "The Subject doesn't created";
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
                    logger.error("Subject doesn't created", e);
                }
            }
    }

    private void editSubjectAction(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException, IOException {
        logger.info("Inside method editSubjectAction");
        try {
            String name = request.getParameter("name");
            long idSubject = Long.valueOf(request.getParameter("idSubject"));
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(idSubject);
            subjectDto.setSubjectName(name);
            boolean success = subjectService.updateSubjectDto(subjectDto);
            if (success) {
                String message ="The subject has been successfully updated.";
                List<SubjectDto> subjectDtoList = subjectService.getSubjectsDto();
                request.setAttribute("idSubject", idSubject);
                request.setAttribute("message", message);
                forwardListSubjects(request, response, subjectDtoList);
            }
        }catch (Exception e) {
            request.setAttribute("Error", "Subject doesn't updated");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("The subject hasn't been updated", e);
        }
    }

    private void removeSubjectAction(HttpServletRequest req, HttpServletResponse resp)
                                   throws ServletException, IOException {
        logger.info("Inside method removeSubjectById");
        try {
            long idSubject = Long.valueOf(req.getParameter("idSubject"));
            boolean confirm = subjectService.deleteSubjectDtoById(idSubject);
            if (confirm) {
                String message = "The subject has been successfully removed.";
                req.setAttribute("message", message);
                List<SubjectDto> subjectDtoList = subjectService.getSubjectsDto();
                forwardListSubjects(req, resp, subjectDtoList);
            }
        }catch (Exception e){
            req.setAttribute("Error", "Subject doesn't removed");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
            logger.error("The subject doesn't removed", e);
        }
    }
}
