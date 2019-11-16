package controller;

import modelDto.GroupDto;
import modelDto.LessonDto;
import org.apache.log4j.Logger;
import serviceDto.GroupServiceDto;
import serviceDto.LessonServiceDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ScheduleGroupServlet", urlPatterns = "/scheduleGroup")
public class ScheduleGroupServlet extends HttpServlet {
    Logger logger = Logger.getLogger(ScheduleGroupServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
          long groupId = Long.valueOf(request.getParameter("groupId"));
          GroupDto groupDto = GroupServiceDto.groupDtoById(groupId);
          List<LessonDto> lessonDtoList = LessonServiceDto.lessonsForGroup(groupDto);
          request.setAttribute("lessonDtoList", lessonDtoList);
          request.setAttribute("groupDto", groupDto);
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedule_group.jsp");
          dispatcher.forward(request, response);

      }catch (Exception e) {

      }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
