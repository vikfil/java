package controller;

import modelDto.GroupDto;
import modelDto.LectorDto;
import org.apache.log4j.Logger;
import repository.GroupRepository;
import repository.LectorRepository;
import service.GroupService;
import service.LectorService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ScheduleServlet", urlPatterns = {"/", "/schedule"})
public class ScheduleServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ScheduleServlet.class.getName());
    private GroupService groupService = new GroupService(new GroupRepository());
    private LectorService lectorService = new LectorService(new LectorRepository());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       logger.info("Inside method doGet");
        try {
           List<GroupDto> groupDtoList = groupService.getGroupsDto();
           List<LectorDto> lectorDtoList = lectorService.getLectorsDto();

           request.setAttribute("groupDtoList", groupDtoList);
           request.setAttribute("lectorDtoList", lectorDtoList);

           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/schedule.jsp");
           dispatcher.forward(request, response);
       }catch (Exception e) {
            String message = "Group or lector list not found";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
           logger.error("Group or lector list not found", e);
       }
    }
}
