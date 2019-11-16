<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Classroom list</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <h2>Classroom</h2>
            <button onclick="location.href='<c:url value="/schedule.jsp"/>'" type="button" class="btn btn-primary  btn-lg " style="width: 100px;"><i class="fa fa-home"></i></button>
            <!-- Classroom List -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                        ${message}
                </div>
            </c:if>
            <form action="classroom" method="post" id="classroomForm" role="form">
                <input type="hidden" id="idClassroom" name="idClassroom">
                <input type="hidden" id="actionClassroom" name="actionClassroom">
                <c:choose>
                    <c:when test="${not empty classroomDtoList}">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>ClassroomNumber</td>
                                <td>ClassroomType</td>
                            </tr>
                            </thead>
                            <c:forEach var="classroomDto" items="${classroomDtoList}">
                                <c:set var="classSuccess" value=""/>
                                <c:if test="${idClassroom == classroomDto.id}">
                                    <c:set var="classSuccess" value="info"/>
                                </c:if>
                                <tr class="${classSuccess}">
                                    <td>${classroomDto.classroomNumber}</td>
                                    <td>${classroomDto.typeRoom}</td>
                                    <td>
                                        <a href='<c:url value="/classroom?idClassroom=${classroomDto.id}&searchAction=searchById"/>' data-toggle="tooltip" title="Edit"><i class="fas fa-edit"></i></a>
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-danger  btn-md" data-toggle="tooltip" title="Remove"
                                                onclick="document.getElementById('actionClassroom').value='remove';
                                                        document.getElementById('idClassroom').value ='${classroomDto.id}';
                                                        document.getElementById('classroomForm').submit();">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                </c:choose>
            </form>
            <button onclick="location.href='<c:url value="/classroom.jsp"/>'" type="button" class="btn btn-primary  btn-md">New classroom</button>
        </div>
    </div>
</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="js/main.js"></script>
</body>
</html>