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
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <h2>Survey registered</h2>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">PATIENT DATA</div>
                </div>
                <div class="panel-body">
                    <p>Name: ${name}</p>
                    <p>Surname: ${surname}</p>
                    <p>Pesel: ${pesel}</p>
                    <p>Sex: ${sex}</p>
                    <p>Email: ${email}</p>
                    <p>Prefered doctor's specialization: ${preferedSpecialization}</p>
                    <p>Prefered day: ${preferedDay}</p>
                </div>
            </div>

            <h4><a type="button" class="btn btn-default btn-lg" href="survey">Next survey</a></h4>
        </div>
    </div>
</div>

<jsp:include page="partials/footer.jsp"/>

</body>
</html>
