<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="partials/meta.jsp" />
</head>
<body>
<div class="container">
  <h1>Register visit:</h1>

  <c:choose>
    <c:when test="${violations.size() == 0}">
      <p class="text-success">Visit Registered!</p>
    </c:when>

    <c:when test="${violations.size() > 0}">
      <p class="bg-warning">Number of violations: ${violations.size()}</p>
      <ul>
        <c:forEach var="violation" items="${violations}">
          <li>${violation.message}</li>
        </c:forEach>
      </ul>
    </c:when>

    <c:otherwise>
      <p class="text-info">Fill in form:</p>
    </c:otherwise>

  </c:choose>

  <form action="survey" method="post">

    <div class="form-group">
      <label for="name">Name</label>
      <input type="text" class="form-control" name="name" id="name" placeholder="Name" value="${name}">
    </div>

    <div class="form-group">
      <label for="surname">Surname</label>
      <input type="text" class="form-control" name="surname" id="surname" placeholder="Surname" value="${surname}">
    </div>

    <div class="form-group">
      <label for="pesel">Pesel</label>
      <input type="text" class="form-control" name="pesel" id="pesel" placeholder="Pesel" value="${pesel}">
    </div>

    <div class="form-group">
      <label for="sex">Sex</label>
      <select class="form-control" name="sex" id="sex" placeholder="Sex" value="${sex}">
        <option name="male" value="MALE">Male</option>
        <option name="female" value="FEMALE">Female</option>
      </select>
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="Email" value="${email}">
    </div>

    <div class="form-group">
      <label for="preferedSpecialization">Prefered doctor's specialization</label>
      <select class="form-control" name="preferedSpecialization" id="preferedSpecialization" placeholder="Choose one" value="${preferedSpecialization}">
        <option value="dentist">dentist</option>
        <option value="urologist">urologist</option>
        <option value="internist">internist</option>
        <option value="laryngologist">laryngologist</option>
      </select>
    </div>

    <div class="form-group">
      <label for="preferedDay">Prefered day</label>
      <select class="form-control" name="preferedDay" id="preferedDay" placeholder="preferedDay" value="${preferedDay}">
        <option name="monday" value="monday">Monday</option>
        <option name="tuesday" value="tuesday">Tuesday</option>
        <option name="wednesday" value="wednesday">Wednesday</option>
        <option name="thursday" value="thursday">Thursday</option>
        <option name="friday" value="friday">Friday</option>
      </select>
    </div>

    <p><input class="btn btn-default" type="submit" value="Submit"></p>

  </form>

  <jsp:include page="partials/footer.jsp" />
</div>
</body>
</html>
