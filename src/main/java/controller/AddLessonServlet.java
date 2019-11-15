package controller;

import model.*;
import modelDto.*;
import org.apache.log4j.Logger;
import serviceDto.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddLessonServlet", urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddLessonServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inside method doPost");
        try {
            LessonDto lessonDto = new LessonDto();
            Week weekday = Week.valueOf(request.getParameter("weekday").toUpperCase());
            lessonDto.setWeekday(weekday);
            int lessonNumber = Integer.valueOf(request.getParameter("lessonNumber"));
            lessonDto.setNumberLesson(lessonNumber);
            long subjectId = Long.valueOf(request.getParameter("subjectId"));
            lessonDto.setSubjectDto(new SubjectDto(subjectId));
            long groupId = Long.valueOf(request.getParameter("groupId"));
            lessonDto.setGroupDto(new GroupDto(groupId));
            long lectorId = Long.valueOf(request.getParameter("lectorId"));
            lessonDto.setLectorDto(new LectorDto(lectorId));
            long classroomId = Long.valueOf(request.getParameter("classroomId"));
            lessonDto.setClassroomDto(new ClassroomDto(classroomId));
            boolean success = LessonServiceDto.addLessonDto(lessonDto);
            if (success) {
                String message ="The lesson has been successfully created.";
                List<LessonDto> lessonDtoList = LessonServiceDto.getLessonsDto();
                request.setAttribute("message", message);
                request.setAttribute("lessonDtoList", lessonDtoList);
                forwardListLessons(request, response, lessonDtoList);
            }else {
                String message ="The lesson doesn't created.";
                List<LessonDto> lessonDtoList = LessonServiceDto.getLessonsDto();
                request.setAttribute("message", message);
                request.setAttribute("lessonDtoList", lessonDtoList);
                forwardListLessons(request, response, lessonDtoList);
            }
        }catch (Exception e) {
            request.setAttribute("Error", "Lesson doesn't created");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Lesson doesn't created", e);
        }
    }

    private void forwardListLessons(HttpServletRequest request, HttpServletResponse response, List lessonDtoList)
            throws ServletException, IOException {
        logger.info("Inside method forwardListLessons");
        String nextJSP = "/lesson_list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("lessonDtoList", lessonDtoList);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inside method doGet");
        List<SubjectDto> subjectDtoList = SubjectServiceDto.getSubjectsDto();
        List<GroupDto> groupDtoList = GroupServiceDto.getGroupsDto();
        List<LectorDto> lectorDtoList = LectorServiceDto.getLectorsDto();
        List<ClassroomDto> classroomDtoList = ClassroomServiceDto.getClassroomsDto();

        request.setAttribute("subjectDtoList", subjectDtoList);
        request.setAttribute("groupDtoList", groupDtoList);
        request.setAttribute("lectorDtoList", lectorDtoList);
        request.setAttribute("classroomDtoList", classroomDtoList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lesson.jsp");
        dispatcher.forward(request, response);
    }
}
