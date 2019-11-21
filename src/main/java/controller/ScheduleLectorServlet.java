package controller;

import model.Week;
import modelDto.LectorDto;
import modelDto.LessonDto;
import org.apache.log4j.Logger;
import repository.*;
import service.LectorService;
import service.LessonService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

@WebServlet(name = "ScheduleLectorServlet", urlPatterns = "/scheduleLector")
public class ScheduleLectorServlet extends HttpServlet {
    private LectorService lectorService = new LectorService(new LectorRepository());
    private LessonService lessonService = new LessonService(new LessonRepository(new SubjectRepository(),
                                                                                 new GroupRepository(),
                                                                                 new LectorRepository(),
                                                                                 new ClassroomRepository()));
    private static Logger logger = Logger.getLogger(ScheduleLectorServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("Inside method doPost");
            long lectorId = Long.valueOf(request.getParameter("lectorId"));
            LectorDto lectorDto = lectorService.lectorDtoById(lectorId);
            List<LessonDto> lessonDtoList = lessonService.lessonsForLector(lectorDto);
            List<LessonDto> mondayList = lessonDtoList.stream().filter(e ->e.getWeekday().equals(Week.MONDAY)).collect(Collectors.toList());
            List<LessonDto> mondayListSorted = mondayList.stream().sorted(Comparator.comparingInt(LessonDto::getNumberLesson)).collect(Collectors.toList());
            request.setAttribute("mondayListSorted", mondayListSorted);
            List<LessonDto> tuesdayList = lessonDtoList.stream().filter(e ->e.getWeekday().equals(Week.TUESDAY)).collect(Collectors.toList());
            List<LessonDto> tuesdayListSorted = tuesdayList.stream().sorted(Comparator.comparingInt(LessonDto::getNumberLesson)).collect(Collectors.toList());
            request.setAttribute("tuesdayListSorted", tuesdayListSorted);
            List<LessonDto> wednesdayList = lessonDtoList.stream().filter(e ->e.getWeekday().equals(Week.WEDNESDAY)).collect(Collectors.toList());
            List<LessonDto> wednesdayListSorted = wednesdayList.stream().sorted(Comparator.comparingInt(LessonDto::getNumberLesson)).collect(Collectors.toList());
            request.setAttribute("wednesdayListSorted", wednesdayListSorted);
            List<LessonDto> thursdayList = lessonDtoList.stream().filter(e ->e.getWeekday().equals(Week.THURSDAY)).collect(Collectors.toList());
            List<LessonDto> thursdayListSorted = thursdayList.stream().sorted(Comparator.comparingInt(LessonDto::getNumberLesson)).collect(Collectors.toList());
            request.setAttribute("thursdayListSorted", thursdayListSorted);
            List<LessonDto> fridayList = lessonDtoList.stream().filter(e ->e.getWeekday().equals(Week.FRIDAY)).collect(Collectors.toList());
            List<LessonDto> fridayListSorted = fridayList.stream().sorted(Comparator.comparingInt(LessonDto::getNumberLesson)).collect(Collectors.toList());
            request.setAttribute("fridayListSorted", fridayListSorted);
            request.setAttribute("lectorDto", lectorDto);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedule_lector.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            String message = "Not found lessson for lector";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Not found lessson for lector", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
