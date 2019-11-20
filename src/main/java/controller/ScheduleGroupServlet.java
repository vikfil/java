package controller;

import model.Week;
import modelDto.GroupDto;
import modelDto.LessonDto;
import org.apache.log4j.Logger;
import repository.*;
import service.GroupService;
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
import java.util.stream.Collectors;

@WebServlet(name = "ScheduleGroupServlet", urlPatterns = "/scheduleGroup")
public class ScheduleGroupServlet extends HttpServlet {
    private GroupService groupService = new GroupService(new GroupRepository());
    private LessonService lessonService = new LessonService(new LessonRepository(new SubjectRepository(),
                                                                                 new GroupRepository(),
                                                                                 new LectorRepository(),
                                                                                 new ClassroomRepository()));
    private static Logger logger = Logger.getLogger(ScheduleGroupServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
          long groupId = Long.valueOf(request.getParameter("groupId"));
          GroupDto groupDto = groupService.groupDtoById(groupId);
          List<LessonDto> lessonDtoList = lessonService.lessonsForGroup(groupDto);

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



          //request.setAttribute("lessonDtoList", lessonDtoList);
          request.setAttribute("groupDto", groupDto);
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedule_group.jsp");
          dispatcher.forward(request, response);

      }catch (Exception e) {

      }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
