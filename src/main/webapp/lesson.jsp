<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add lesson</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form action="addLesson" method="post"  role="form" class="form" data-toggle="validator" >
        <h2 class="h2">Lesson</h2>
        <div class="form-group col-xs-4">
            <label for="weekday" class="control-label col-xs-4">Weekday:</label>
            <select name="weekday" class="form-control" id="weekday">
                <option value="monday">Monday</option>
                <option value="tuesday">Tuesday</option>
                <option value="wednesday">Wednesday</option>
                <option value="thursday">Thursday</option>
                <option value="friday">Friday</option>
                <option value="saturday">Saturday</option>
                <option value="sunday">Sunday</option>
            </select>
            <label for="lessonNumber" class="control-label col-xs-4">Lesson number:</label>
            <select name="lessonNumber" id="lessonNumber" class="form-control">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
            </select>
            <label for="lessonSubject" class="control-label col-xs-4">Subject:</label>
            <select required name="subjectId" id="lessonSubject" class="form-control">
                <c:forEach items="${subjectDtoList}" var="subjectDto">
                    <option value="${subjectDto.id}">${subjectDto}</option>
                </c:forEach>
            </select>
            <label for="lessonGroup" class="control-label col-xs-4">Group:</label>
            <select required name="groupId" id="lessonGroup" class="form-control">
                <c:forEach items="${groupDtoList}" var="groupDto">
                    <option value="${groupDto.id}">${groupDto}</option>
                </c:forEach>
            </select>
            <label for="lessonLector" class="control-label col-xs-4">Lector:</label>
            <select required name="lectorId" id="lessonLector" class="form-control">
                <c:forEach items="${lectorDtoList}" var="lectorDto">
                    <option value="${lectorDto.id}">${lectorDto}</option>
                </c:forEach>
            </select>
            <label for="lessonClassroom" class="control-label col-xs-4">Classroom:</label>
            <select required name="classroomId" id="lessonClassroom" class="form-control">
                <c:forEach items="${classroomDtoList}" var="classroomDto">
                    <option value="${classroomDto.id}">${classroomDto}</option>
                </c:forEach>
            </select>
            <br></br>
            <button type="submit" class="btn btn-success  btn-md">Create</button>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="js/main.js"></script>
</body>
</html>
