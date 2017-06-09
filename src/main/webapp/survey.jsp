<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="partials/meta.jsp"/>
</head>
<body id="start" data-spy="scroll" data-target=".navbar" data-offset="60">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">
                <span class="glyphicon glyphicon-link"></span> PEANUT MEDICINE</a>
        </div>
        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="index.html#about" />">ABOUT US</a></li>
                <li><a href="#">APPOINTMENTS</a></li>
                <li><a href="#">NEW PATIENTS</a></li>
                <li><a href="#">PRESCRIPTIONS</a></li>
                <li><a href="<c:url value="index.html#services" />">SERVICES</a></li>
                <li><a href="<c:url value="index.html#staff" />">STAFF</a></li>
                <li><a href="<c:url value="index.html#contact" />">CONTACT</a></li>
                <li>
                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid bg-grey">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <h2>Fill registration form</h2>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">Registration form</div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="survey" method="post">

                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" id="name" placeholder="Name"
                                       value="${name}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="surname" class="col-sm-2 control-label">Surname</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="surname" id="surname"
                                       placeholder="Surname"
                                       value="${surname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pesel" class="col-sm-2 control-label">Pesel</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="pesel" id="pesel" placeholder="Pesel"
                                       value="${pesel}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="sex" class="col-sm-2 control-label">Sex</label>
                            <div class="col-sm-9">
                                <select class="form-control selectpicker" name="sex" id="sex">
                                    <option value="" disabled selected>Sex</option>
                                    <option name="male" value="MALE">Male</option>
                                    <option name="female" value="FEMALE">Female</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-9">
                                <input type="email" class="form-control" name="email" id="email" placeholder="Email"
                                       value="${email}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="preferedSpecialization" class="col-sm-2 control-label">Specialist</label>
                            <div class="col-sm-9">
                                <select class="form-control selectpicker" name="preferedSpecialization"
                                        id="preferedSpecialization">
                                    <option value="" disabled selected>Choose preferred specialist</option>
                                    <option value="dentist">dentist</option>
                                    <option value="urologist">urologist</option>
                                    <option value="internist">internist</option>
                                    <option value="laryngologist">laryngologist</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="preferedDay" class="col-sm-2 control-label">Prefered day</label>
                            <div class="col-sm-9">
                                <select class="form-control selectpicker" name="preferedDay" id="preferedDay">
                                    <option value="" disabled selected>Choose preferred day for your visit</option>
                                    <option name="monday" value="monday">Monday</option>
                                    <option name="tuesday" value="tuesday">Tuesday</option>
                                    <option name="wednesday" value="wednesday">Wednesday</option>
                                    <option name="thursday" value="thursday">Thursday</option>
                                    <option name="friday" value="friday">Friday</option>
                                </select>
                            </div>
                        </div>
                        <br>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <c:choose>
                                    <c:when test="${violations.size() > 0}">
                                        <p class="bg-warning">Number of violations: ${violations.size()}</p>
                                        <ul>
                                            <c:forEach var="violation" items="${violations}">
                                                <li>${violation.message}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-info">Please complete all required fields</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <input class="btn btn-default" type="submit" value="Register">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="partials/footer.jsp"/>

<script>
    $('select[name=sex]').val("${sex}");
    $('select[name=preferedSpecialization]').val("${preferedSpecialization}");
    $('select[name=preferedDay]').val("${preferedDay}");
    $('.selectpicker').selectpicker('refresh');
</script>

</div>
</body>
</html>
