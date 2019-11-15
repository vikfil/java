package controller;

import model.Lector;
import modelDto.LectorDto;
import org.apache.log4j.Logger;
import repository.LectorRepository;
import serviceDto.LectorServiceDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SaveLectorServlet",  urlPatterns = "/lector")
public class LectorServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LectorServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException {
        logger.info("Inside method doPost");
        String action = request.getParameter("actionLector");
        switch (action) {
            case "add":
                logger.info("Before method addLectorAction");
                addLectorAction(request, response);
                break;
            case "edit":
                logger.info("Before method editLectorAction");
                editLectorAction(request, response);
                break;
            case "remove":
                logger.info("Before method removeLectorAction");
                removeLectorAction(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        logger.info("Inside method doGet");
        String action = request.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    logger.info("Before method searchLectorById");
                    searchLectorById(request, response);
                    break;
            }
        }else {
            List<LectorDto> result = LectorServiceDto.getLectorsDto();
            forwardListLectors(request, response, result);
        }
    }

    private void searchLectorById(HttpServletRequest request, HttpServletResponse response)
                                  throws ServletException, IOException {
        logger.info("Inside method searchLectorById");
        try {
            long idLector = Long.valueOf(request.getParameter("idLector"));
            LectorDto lectorDto = LectorServiceDto.lectorDtoById(idLector);
            request.setAttribute("lectorDto", lectorDto);
            request.setAttribute("actionLector", "edit");
            String nextJSP = "/lector.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }catch ( SQLException | ClassNotFoundException ex) {
            request.setAttribute("Error", "Lector doesn't found");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Lector doesn't found", ex);
        }
    }

    private void forwardListLectors(HttpServletRequest request, HttpServletResponse response, List lectorDtoList)
                                    throws ServletException, IOException {
        logger.info("Inside method forwardListLectors");
        String nextJSP = "/lector_list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("lectorDtoList", lectorDtoList);
        dispatcher.forward(request, response);
    }

    private void addLectorAction(HttpServletRequest request, HttpServletResponse response)
                                 throws ServletException, IOException {
        logger.info("Inside method addLectorAction");
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            LectorDto lectorDto = new LectorDto();
            lectorDto.setFirstName(firstName);
            lectorDto.setLastName(lastName);
            long idLector = LectorServiceDto.addLectorDto(lectorDto);
            List<LectorDto> lectorDtoList = LectorServiceDto.getLectorsDto();
            request.setAttribute("idLector", idLector);
            String message = "The new Lector has been successfully created";
            request.setAttribute("message", message);
            forwardListLectors(request, response, lectorDtoList);
        }catch (Exception e) {
            request.setAttribute("Error", "Lector doesn't created");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Lector doesn't created", e);
        }
    }

    private void editLectorAction(HttpServletRequest request, HttpServletResponse response)
                                  throws ServletException, IOException {
        logger.info("Inside method editLectorAction");
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            long idLector = Long.valueOf(request.getParameter("idLector"));
            LectorDto lectorDto = new LectorDto();
            lectorDto.setFirstName(firstName);
            lectorDto.setLastName(lastName);
            lectorDto.setId(idLector);
            boolean success = LectorServiceDto.updateLectorDto(lectorDto);
            if (success) {
                String message ="The lector has been successfully updated.";
                List<LectorDto> lectorDtoList = LectorServiceDto.getLectorsDto();
                request.setAttribute("idLector", idLector);
                request.setAttribute("message", message);
                forwardListLectors(request, response, lectorDtoList);
            }
        }catch (Exception e) {
            request.setAttribute("Error", "Lector doesn't updated");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("The lector hasn't been updated", e);
        }
    }

    private void removeLectorAction(HttpServletRequest req, HttpServletResponse resp)
                                  throws ServletException, IOException {
        logger.info("Inside method removeLectorById");
        try {
            long idLector = Long.valueOf(req.getParameter("idLector"));
            boolean confirm = LectorServiceDto.deleteLectorDtoById(idLector);
            if (confirm) {
                String message = "The lector has been successfully removed.";
                req.setAttribute("message", message);
                List<LectorDto> lectorDtoList = LectorServiceDto.getLectorsDto();
                forwardListLectors(req, resp, lectorDtoList);
            }
        }catch (Exception e){
            req.setAttribute("Error", "Lector doesn't removed");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
            logger.error("The lector doesn't removed", e);
        }
    }
}
