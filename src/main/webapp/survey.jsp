<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="partials/meta.jsp" />
</head>
<body>
  <h1>Register visit:</h1>

  <c:choose>
    <c:when test="${violations.size() == 0}">
      <p>Visit Registered!</p>
    </c:when>
    <c:otherwise>
      <p>Number of violations: ${violations.size()}</p>

      <ul>
        <c:forEach var="violation" items="${violations}">
          <li>${violation.message}</li>
        </c:forEach>
      </ul>
    </c:otherwise>
  </c:choose>

  <form action="survey" method="post">
    <p>Name: <input type="text" name="name" value="${name}"></p>
    <p>Surname: <input type="text" name="Surname" value="${Surname}"></p>
    <p>Sex:

      <select name="sex" value="${sex}">
        <option name = "male" value="male"></option>
        <option name = "female" value="female"></option>
      </select>

    </p>
    <p>Email: <input type="text" name="pesel" value="${pesel}"></p>

    <p>Preferred specialization:
      <select name="specialization" value="${specialization}">
        <option name = "" value=""></option>
        <option name = "" value=""></option>
        <option name = "" value=""></option>
      </select>
    </p>
    </p>

    <p><button type="submit">Send</button> </p>
  </form>

  <jsp:include page="partials/footer.jsp" />
</body>
</html>
