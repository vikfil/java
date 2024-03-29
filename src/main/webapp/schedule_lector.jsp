<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

</body>
</html>
<html lang="en">
<head>
    <title>Schedule for lector</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<div class="container">
<div class="row">
    <div class="col">
        <h2>Schedule for lector: ${lectorDto}</h2>
        <button onclick="location.href='<c:url value="/schedule"/>'" type="button" class="btn btn-primary  btn-lg home" style="width: 100px;"><i class="fa fa-home"></i></button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Weekday</th>
                <th>Lesson number</th>
                <th>Subject</th>
                <th>Group</th>
                <th>Classroom</th>
            </tr>
            </thead>
            <c:forEach var="lessonDto" items="${mondayListSorted}">
                <tr>
                    <td>${lessonDto.weekday}</td>
                    <td>${lessonDto.numberLesson}</td>
                    <td>${lessonDto.subjectDto}</td>
                    <td>${lessonDto.groupDto}</td>
                    <td>${lessonDto.classroomDto}</td>
                </tr>
            </c:forEach>
            <c:forEach var="lessonDto" items="${tuesdayListSorted}">
                <tr>
                    <td>${lessonDto.weekday}</td>
                    <td>${lessonDto.numberLesson}</td>
                    <td>${lessonDto.subjectDto}</td>
                    <td>${lessonDto.groupDto}</td>
                    <td>${lessonDto.classroomDto}</td>
                </tr>
            </c:forEach>
            <c:forEach var="lessonDto" items="${wednesdayListSorted}">
                <tr>
                    <td>${lessonDto.weekday}</td>
                    <td>${lessonDto.numberLesson}</td>
                    <td>${lessonDto.subjectDto}</td>
                    <td>${lessonDto.groupDto}</td>
                    <td>${lessonDto.classroomDto}</td>
                </tr>
            </c:forEach>
            <c:forEach var="lessonDto" items="${thursdayListSorted}">
                <tr>
                    <td>${lessonDto.weekday}</td>
                    <td>${lessonDto.numberLesson}</td>
                    <td>${lessonDto.subjectDto}</td>
                    <td>${lessonDto.groupDto}</td>
                    <td>${lessonDto.classroomDto}</td>
                </tr>
            </c:forEach>
            <c:forEach var="lessonDto" items="${fridayListSorted}">
                <tr>
                    <td>${lessonDto.weekday}</td>
                    <td>${lessonDto.numberLesson}</td>
                    <td>${lessonDto.subjectDto}</td>
                    <td>${lessonDto.groupDto}</td>
                    <td>${lessonDto.classroomDto}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="js/main.js"></script>
</body>
</html>