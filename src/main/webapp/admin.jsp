<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="partials/meta.jsp"/>
</head>
<body id="start" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="partials/navbar.jsp"/>

<div class="container-fluid bg-grey">

    <div class="row" id="myMenu">
        <div class="col-sm-3 col-left">
            <nav class="navbar navbar-inverse navbar-fixed-left">
                <div class="panel-heading"><h3>ADMIN PANEL</h3></div>
                <ul class="nav nav-pills nav-stacked">
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#dashboard">Dashboard</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#registerForms">Registration forms</a>
                    </li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#visits">Visits</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#users">Users</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#doctors">Doctors</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#statistics">Statistics</a></li>
                    <li><a href="icd-9">ICD-9 Classification</a></li>
                    <%--<li><a data-toggle="collapse" data-parent="#myMenu" href="#settings">Settings</a></li>--%>
                </ul>
            </nav>
        </div>

        <div class="col-sm-9 ">
            <div class="row accordion-group">

                <div class="panel panel-default panel-collapse collapse menu_collapse in" id="dashboard">
                    <div class="panel-heading"><h3>DASHBOARD</h3></div>

                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <a data-toggle="collapse" data-parent="#myMenu" href="#users">
                                        <div class="well text-center">
                                            <span class="glyphicon glyphicon-user logo-medium"></span>
                                            <h2>Users</h2>
                                            <span class="badge">${users.size()}</span>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-sm-3">
                                    <a data-toggle="collapse" data-parent="#myMenu" href="#registerForms">
                                        <div class="well well text-center">
                                            <span class="glyphicon glyphicon-list-alt logo-medium"></span>
                                            <h2>Reg.forms</h2>
                                            <span class="badge">${surveys.size()}</span>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-sm-3">
                                    <a data-toggle="collapse" data-parent="#myMenu" href="#visits">
                                        <div class="well text-center">
                                            <span class="glyphicon glyphicon-calendar logo-medium"></span>
                                            <h2>Visits</h2>
                                            <span class="badge">${appointments.size()}</span>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-sm-3">
                                    <a data-toggle="collapse" data-parent="#myMenu" href="#doctors">
                                        <div class="well text-center">
                                            <span class="glyphicon glyphicon-user logo-medium"></span>
                                            <h2>Doctors</h2>
                                            <span class="badge">${doctors.size()}</span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="panel-heading">Contact request!</div>
                            <div class="panel-body">
                                <p>Name: Adam</p>
                                <p>Mail: adam@firma.com</p>
                                <h6>Sent on 2017-06-07 20:43</h6>
                                <p>
                                    Duis posuere scelerisque condimentum. Ut eu eros quam. Nullam sodales justo
                                    vitae est dapibus vehicula. Aliquam quis rhoncus ligula, efficitur congue
                                    nunc. Nam vestibulum tortor at varius consectetur. Nunc vestibulum, leo
                                    vitae pretium vehicula, felis elit sollicitudin augue, vitae euismod dui
                                    nisl non felis.
                                </p>
                                <%--<div class="btn-group" role="group" aria-label="What to do with message?">--%>
                                    <%--<button type="button" class="btn btn-mini btn-default">Read</button>--%>
                                    <%--<button type="button" class="btn btn-mini btn-default">Answer</button>--%>
                                    <%--<button type="button" class="btn btn-mini btn-default">Remove</button>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default panel-collapse collapse menu_collapse" id="registerForms">
                <div class="panel-heading"><h3>REGISTRATION FORMS</h3></div>
                <c:forEach var="survey" items="${surveys}">
                    <div class="well" id="survey${survey.id}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">${survey.name}
                                    <tr>
                                    <tr> ${survey.surname}
                                </div>
                            </div>
                            <div class="panel-body">
                                <p>Pesel: ${survey.pesel}</p>
                                <p>Sex: ${survey.sex}</p>
                                <p>Email: ${survey.email}</p>
                                <p>Prefered doctor's specialization: ${survey.preferedSpecialization}</p>
                                <p>Prefered day: ${survey.preferedDay}</p>

                                <c:choose>
                                    <c:when test="${survey.appointments.size() == 0}">
                                        <p><a href="admin/appoint?survey=${survey.id}">Make appointment</a></p>
                                    </c:when>

                                    <c:when test="${survey.appointments.size() == 1}">
                                        <p>Planned visit:
                                                ${survey.appointments[0].doctorName} ${survey.appointments[0].doctorSurname}
                                            [${survey.appointments[0].doctorSpecialization}] - ${survey.appointments[0].term}</p>
                                    </c:when>

                                    <c:otherwise>
                                        <p>${survey.appointments[0].doctorName} ${survey.appointments[0].doctorSurname}</p>
                                        <p>Choose one term from proposed visits:</p>
                                        <form method="POST" action="admin/appoint">
                                            <input type="radio" name="appointment" value="${survey.appointments[0].id}" checked>
                                                ${survey.appointments[0].term}
                                            <input type="radio" name="appointment" value="${survey.appointments[1].id}">
                                                ${survey.appointments[1].term}
                                            <input type="submit" value="Choose">
                                        </form>

                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                    </div>
                </c:forEach>


            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="visits">
                <div class="panel-heading"><h3>VISITS</h3></div>

                <c:forEach var="appointment" items="${appointments}">
                    <div class="well">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">${appointment.survey.name} ${appointment.survey.surname}</div>
                            </div>
                            <div class="panel-body">
                                <p>${appointment.doctorName} ${appointment.doctorSurname} [${appointment.doctorSpecialization}]</p>
                                <p>Term: ${appointment.term}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="users">
                <div class="panel-heading"><h3>USERS</h3></div>
                <c:forEach var="user" items="${users}">
                    <div class="well">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">${user.name}</div>
                            </div>
                            <div class="panel-body">
                                <p>Email: ${user.email}</p>
                                <p>Admin: ${user.admin}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="doctors">
                <div class="panel-heading"><h3>DOCTORS</h3></div>
                <c:forEach var="doctor" items="${doctors}">
                    <div class="well">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">${doctor.name} ${doctor.surname}, MD</div>
                            </div>
                            <div class="panel-body">
                                <p>Specialization: ${doctor.specialization}</p>
                                <p>Calendar file: ${doctor.calendarFile}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="statistics">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm-6"><h3>STATISTICS</h3></div>
                        <div class="col-sm-6">
                            <h3 class="pull-right">Reports API
                                <c:choose>
                                    <c:when test="${reportsModuleConnectionStatus == 200}">
                                        <span class="glyphicon glyphicon-transfer"></span> OK
                                        (${reportsModuleConnectionStatus})
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-transfer"></span>
                                        <span class="glyphicon glyphicon-alert"></span>
                                        (${reportsModuleConnectionStatus})
                                    </c:otherwise>
                                </c:choose>
                            </h3>

                        </div>
                    </div>
                </div>

                <br><br>
                <div class="panel panel-default">
                    <div class="panel-heading">Preferred specializations report</div>
                    <div class="panel-body">
                        <p>This report shows how many patients registered with the doctor of a particular specialty</p>
                        <br>
                        <div class="row">
                            <div class="col-sm-6">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Doctor speciality</th>
                                        <th>Number of patients</th>
                                    </tr>
                                    <c:forEach var="adminStatistics" items="${adminStatistics}">
                                        <tr>
                                            <td>${adminStatistics.key}</td>
                                            <td>${adminStatistics.value}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="col-sm-6 text-center">
                                <h1><span class="glyphicon glyphicon-stats"></span></h1>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">User activities</div>
                    <div class="panel-body">
                        <p>This report shows users activities</p>
                        <br>
                        <table class="table table-bordered">
                            <tr>
                                <th>User email</th>
                                <th>Login</th>
                                <th>Logout</th>
                            </tr>
                            <c:forEach var="userActivity" items="${usersActivities}">
                                <tr>
                                    <td>${userActivity.user.email}</td>
                                    <td>${userActivity.loginTime}</td>
                                    <td>${userActivity.logoutTime}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="settings">
                <div class="panel-heading"><h3>Setings</h3></div>
            </div>
        </div>
    </div>

</div>

<jsp:include page="partials/footer.jsp"/>

<script>
    var hash = $(location).attr('hash');
    if(hash == '#registerForms')
    {
        $('#dashboard').collapse('hide');
        $('#registerForms').collapse('show');
    }

    if(hash.substring(1, 7) == 'survey')
    {
        $('#dashboard').collapse('hide');
        $('#registerForms').collapse('show');
        $('body').scrollTo('#'+hash);
    }

    var $myMenu = $('#myMenu');
    $myMenu.on('show.bs.collapse', '.menu_collapse', function () {
        $myMenu.find('.menu_collapse.in').collapse('hide');
    });
</script>

</body>
</html>
