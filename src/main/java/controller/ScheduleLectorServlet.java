package controller;

import modelDto.LectorDto;
import modelDto.LessonDto;
import org.apache.log4j.Logger;
import serviceDto.LectorServiceDto;
import serviceDto.LessonServiceDto;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ScheduleLectorServlet", urlPatterns = "/scheduleLector")
public class ScheduleLectorServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ScheduleLectorServlet.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("Inside method doPost");
            long lectorId = Long.valueOf(request.getParameter("lectorId"));
            LectorDto lectorDto = LectorServiceDto.lectorDtoById(lectorId);
            List<LessonDto> dtoList = LessonServiceDto.lessonsForLector(lectorDto);
            request.setAttribute("dtoList", dtoList);
            request.setAttribute("lectorDto", lectorDto);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedule_lector.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            logger.error("Not found lessson for lector", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
