package controller;

import modelDto.ClassroomDto;
import modelDto.GroupDto;
import org.apache.log4j.Logger;
import repository.GroupRepository;
import service.GroupService;

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

@WebServlet(name = "SaveGroupServlet", value = "/group")
public class GroupServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(GroupServlet.class.getName());
    private GroupService groupService = new GroupService(new GroupRepository());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                          throws ServletException, IOException {
        logger.info("Inside method doPost");
        String action = request.getParameter("actionGroup");
        switch (action) {
            case "add":
                logger.info("Before method addGroupAction");
                addGroupAction(request, response);
                break;
            case "edit":
                logger.info("Before method editGroupAction");
                editGroupAction(request, response);
                break;
            case "remove":
                logger.info("Before method removeGroupAction");
                removeGroupAction(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException {
        logger.info("Inside method goGet");
        String action = request.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    logger.info("Before method searchGroupById");
                    searchGroupById(request, response);
                    break;
            }
        }else {
            List<GroupDto> result = groupService.getGroupsDto();
            forwardListGroups(request, response, result);
        }
    }

    private void searchGroupById(HttpServletRequest request, HttpServletResponse response)
                                throws ServletException, IOException {
        logger.info("Inside method searchGroupById");
        try {
            long idGroup = Long.valueOf(request.getParameter("idGroup"));
            GroupDto groupDto = groupService.groupDtoById(idGroup);
            request.setAttribute("groupDto", groupDto);
            request.setAttribute("actionGroup", "edit");
            String nextJSP = "/group.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("Error", "Group doesn't found");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Group doesn't found", ex);
        }
    }

    private void forwardListGroups(HttpServletRequest request, HttpServletResponse response, List groupDtoList)
                                       throws ServletException, IOException {
        logger.info("Inside method forwardListGroups");
        String nextJSP = "/group_list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("groupDtoList", groupDtoList);
        dispatcher.forward(request, response);
    }

    private void addGroupAction(HttpServletRequest request, HttpServletResponse response)
                                throws ServletException, IOException {
        logger.info("Inside method addGroupAction");

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            String groupNumber = request.getParameter("groupNumber");
            String groupName = request.getParameter("groupName");
            GroupDto groupDto = new GroupDto(groupNumber, groupName);
//            groupDto.setGroupNumber(groupNumber);
//            groupDto.setGroupName(groupName);
            Set<ConstraintViolation<GroupDto>> violations = validator.validate(groupDto);
            if (violations.size() > 0) {
                for (ConstraintViolation<GroupDto> violation : violations) {
                    String message = violation.getMessage();
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
                }
            }else {
                try {
                    long idGroup = groupService.addGroupDto(groupDto);
                    List<GroupDto> groupDtoList = groupService.getGroupsDto();
                    request.setAttribute("idGroup", idGroup);
                    String message = "The new group has been successfully created";
                    request.setAttribute("message", message);
                    forwardListGroups(request, response, groupDtoList);
                } catch (Exception e) {
                    String message = "The Group doesn't created";
                    request.setAttribute("message", message);
                    //request.setAttribute("Error", "Group doesn't created");
                    getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
                    logger.error("Group doesn't created", e);
                }
            }
    }

    private void editGroupAction(HttpServletRequest request, HttpServletResponse response)
                                 throws ServletException, IOException {
        logger.info("Inside method editGroupAction");
        try {
            String groupNumber = request.getParameter("groupNumber");
            String groupName = request.getParameter("groupName");
            long idGroup = Long.valueOf(request.getParameter("idGroup"));
            GroupDto groupDto = new GroupDto();
            groupDto.setGroupNumber(groupNumber);
            groupDto.setGroupName(groupName);
            groupDto.setId(idGroup);
            boolean success = groupService.updateGroupDto(groupDto);
            if (success) {
                String message = "The group has been successfully updated.";
                List<GroupDto> groupDtoList = groupService.getGroupsDto();
                request.setAttribute("idGroup", idGroup);
                request.setAttribute("message", message);
                forwardListGroups(request, response, groupDtoList);
            }
        }catch(Exception e) {
            request.setAttribute("Error", "Group doesn't updated");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(request, response);
            logger.error("Group doesn't updated", e);
        }
    }

    private void removeGroupAction(HttpServletRequest req, HttpServletResponse resp)
                                            throws ServletException, IOException {
        logger.info("Inside method removeGroupAction");
        try {
            long idGroup = Long.valueOf(req.getParameter("idGroup"));
            boolean confirm = groupService.deleteGroupDtoById(idGroup);
            if (confirm) {
                String message = "The group has been successfully removed.";
                req.setAttribute("message", message);
                List<GroupDto> groupDtoList = groupService.getGroupsDto();
                forwardListGroups(req, resp, groupDtoList);
            }
        }catch (Exception e) {
            req.setAttribute("Error", "Group doesn't removed");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
            logger.error("Group doesn't removed", e);
        }
    }
}
