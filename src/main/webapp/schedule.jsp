<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Schedule</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>

   <div class="container">
       <header>
       <div class="row">
           <div class="col">
               <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
               <div class="breadcrumb ">
                   <a class="breadcrumb-item" href="<c:url value='/deleteLesson'/>">Lesson</a>
                   <a class="breadcrumb-item" href="<c:url value='/subject'/>">Subject</a>
                   <a class="breadcrumb-item" href="<c:url value='/group'/>">Group</a>
                   <a class="breadcrumb-item" href="<c:url value='/lector'/>">Lector</a>
                   <a class="breadcrumb-item" href="<c:url value='/classroom'/>">Classroom</a>
               </div>

               <h1>Schedule</h1>

           </div>
       </div>
</header>

    <div class="row schedule">
        <div class="col-sm-6 ">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Show schedule for Group</h5>
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
                    <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#flipFlop">
                        Please select the group
                    </button>
                    <!-- Модальное окно -->
                    <div class="modal fade" id="flipFlop" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title" id="modalLabel">Group</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Закрыть">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="scheduleGroup" method="post" >
                                    <select class="form-control" required name="groupId" id="lessonGroup">
                                        <c:forEach items="${groupDtoList}" var="groupDto">
                                            <option value="${groupDto.id}">${groupDto}</option>
                                        </c:forEach>
                                    </select>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-success">Submit</button>
                                </div>
                                </form>
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
            </div>
          <div class="col-sm-6">
              <div class="card">
                  <div class="card-body">
                      <h5 class="card-title">Show schedule for Lector</h5>
                      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
                      <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#flipFlop2">
                          Please select the lector
                      </button>
                      <!-- Модальное окно -->
                      <div class="modal fade" id="flipFlop2" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                              <div class="modal-content">
                                  <div class="modal-header">
                                      <h4 class="modal-title text-center" id="modalLabel2" >Lector</h4>
                                      <button type="button" class="close" data-dismiss="modal" aria-label="Закрыть">
                                          <span aria-hidden="true">&times;</span>
                                      </button>
                                  </div>
                                  <form action="scheduleLector" method="post">
                                      <select class="form-control" required name="lectorId" id="lessonLector">
                                          <c:forEach items="${lectorDtoList}" var="lectorDto">
                                              <option value="${lectorDto.id}">${lectorDto}</option>
                                          </c:forEach>
                                      </select>
                                      <div class="modal-footer">
                                          <button type="submit" class="btn btn-success">Submit</button>
                                      </div>
                                  </form>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
        </div>
      </div>
       <div class="row">
           <div class="col">
               <footer>
               </footer>
           </div>
       </div>
   </div>










<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="js/main.js"></script>
</body>
</html>
