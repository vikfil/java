package controller;

import modelDto.GroupDto;
import modelDto.LectorDto;
import modelDto.LessonDto;
import serviceDto.GroupServiceDto;
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

@WebServlet(name = "ScheduleServlet", urlPatterns = "/schedule")
public class ScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GroupDto> groupDtoList = GroupServiceDto.getGroupsDto();
        List<LectorDto> lectorDtoList = LectorServiceDto.getLectorsDto();

        request.setAttribute("groupDtoList", groupDtoList);
        request.setAttribute("lectorDtoList", lectorDtoList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedule.jsp");
        dispatcher.forward(request, response);
    }
}
