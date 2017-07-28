<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="partials/meta.jsp"/>
    <%--<jsp:useBean id="adminStatistics" scope="request" type="org.hibernate.action.internal.CollectionAction"/>--%>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

        // Load the Visualization API and the corechart package.
        google.charts.load('current', {'packages':['corechart']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);


        function drawChart() {

            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Topping');
            data.addColumn('number', 'Slices');
            data.addRows([
                ['urologist', 2],
                ['internist', 2],
                ['laryngologist', 2],
                ['dentist', 2],
            ]);

            // Set chart options
            var options = {'title':' ',
                'width':700,
                'height':500};

            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>


</head>
<body id="start" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="partials/navbar.jsp"/>

<div class="container-fluid bg-grey">

    <div class="row" id="myMenu">
        <div class="col-sm-3 col-left">
            <nav class="navbar navbar-inverse" role="navigation">
                <div class="panel-heading"><h3>ADMIN PANEL</h3></div>
                <ul class="nav nav-pills nav-stacked">
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#dashboard">Dashboard</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#registerForms">Registration forms</a>
                    </li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#visits">Visits</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#users">Users</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#doctors">Doctors</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#statistics">Statistics</a></li>
                    <li><a data-toggle="collapse" data-parent="#myMenu" href="#settings">Charts</a></li>
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
                                            <span class="badge">0</span>
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
                                <div class="btn-group" role="group" aria-label="What to do with message?">
                                    <button type="button" class="btn btn-mini btn-default">Read</button>
                                    <button type="button" class="btn btn-mini btn-default">Answer</button>
                                    <button type="button" class="btn btn-mini btn-default">Remove</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel panel-default panel-collapse collapse menu_collapse" id="registerForms">
                <div class="panel-heading"><h3>REGISTRATION FORMS</h3></div>
                <c:forEach var="survey" items="${surveys}">
                    <div class="well">
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
                            </div>
                        </div>
                    </div>
                </c:forEach>


            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="visits">
                <div class="panel-heading"><h3>VISITS</h3></div>
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
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="statistics">
                <div class="panel-heading"><h3>STATISTICS</h3></div>
                <c:forEach var="adminStatistics" items="${adminStatistics}">
                    <div class="well">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="panel-title">
                                    <p> ${adminStatistics.key}</p>
                                </div>
                            </div>
                            <div class="panel-body">
                                <p> ${adminStatistics.value}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="panel panel-default panel-collapse collapse menu_collapse" id="settings">
                <div class="panel-heading"><h3>Charts</h3></div>
                <div class="well">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <p>How many specializations have been chosen</p>
                            </div>
                        </div>
                        <div class="panel-body">
                            <p> <div id="chart_div"></div></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>

</div>

<jsp:include page="partials/footer.jsp"/>

<script>
    var $myMenu = $('#myMenu');
    $myMenu.on('show.bs.collapse', '.menu_collapse', function () {
        $myMenu.find('.menu_collapse.in').collapse('hide');
    });
</script>

</body>
</html>
