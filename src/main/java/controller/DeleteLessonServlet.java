package controller;

import modelDto.*;
import org.apache.log4j.Logger;
import serviceDto.LessonServiceDto;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LessonServlet", urlPatterns = "/deleteLesson")
public class DeleteLessonServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DeleteLessonServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                          throws ServletException, IOException {
        logger.info("Inside method doPost");
        try {
        long idLesson = Long.valueOf(request.getParameter("idLesson"));
        boolean confirm = LessonServiceDto.deleteLessonDtoById(idLesson);
                if (confirm) {
                    String message = "The lesson has been successfully removed.";
                    List<LessonDto> lessonDtoList = LessonServiceDto.getLessonsDto();
                    request.setAttribute("message", message);
                    request.setAttribute("lessonDtoList", lessonDtoList);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lesson_list.jsp");
                    dispatcher.forward(request, response);
                }
            }catch (Exception e){
                request.setAttribute("Error", "Lesson doesn't removed");
                getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
                logger.error("The lesson doesn't removed", e);
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        try {
            List<LessonDto> lessonDtoList = LessonServiceDto.getLessonsDto();
            request.setAttribute("lessonDtoList", lessonDtoList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lesson_list.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e ) {
            request.setAttribute("Error", "Lesson doesn't found");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("The lesson doesn't found", e);
        }
    }
}

