<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="partials/meta.jsp" />
</head>
<body>
<div class="container">
  <h1>Survey registered:</h1>

  <p>Name: ${name}</p>
  <p>Surname: ${surname}</p>
  <p>Pesel: ${pesel}</p>
  <p>Sex: ${sex}</p>
  <p>Email: ${email}</p>
  <p>Prefered doctor's specialization: ${preferedSpecialization}</p>
  <p>Prefered day: ${preferedDay}</p>

  <p></p>
  <h4><a href="survey">Next survey</a></h4>
  <jsp:include page="partials/footer.jsp" />
</div>
</body>
</html>
