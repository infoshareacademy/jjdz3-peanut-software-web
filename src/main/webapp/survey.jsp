<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="partials/meta.jsp" />
</head>
<body>
<div class="container">
  <h1>Rejestracja wizyty:</h1>

  <c:choose>
    <c:when test="${violations.size() == 0}">
      <p class="text-success">Ankieta zapisana.</p>
    </c:when>

    <c:when test="${violations.size() > 0}">
      <p class="bg-warning">Niepoprawne pola: ${violations.size()}</p>
      <ul>
        <c:forEach var="violation" items="${violations}">
          <li>${violation.message}</li>
        </c:forEach>
      </ul>
    </c:when>

    <c:otherwise>
      <p class="text-primary">Wypełnij ankietę abyśmy mogli wybrać dla ciebie odpowiedniego lekarza i datę.</p>
    </c:otherwise>

  </c:choose>

  <form action="survey" method="post">

    <div class="form-group">
      <label for="name">Imię</label>
      <input type="text" class="form-control" name="name" id="name" placeholder="imię" value="${name}">
    </div>

    <div class="form-group">
      <label for="surname">Nazwisko</label>
      <input type="text" class="form-control" name="surname" id="surname" placeholder="nazwisko" value="${surname}">
    </div>

    <div class="form-group">
      <label for="pesel">Pesel</label>
      <input type="text" class="form-control" name="pesel" id="pesel" placeholder="Pesel" value="${pesel}">
    </div>

    <div class="form-group">
      <label for="sex">Płeć</label>
      <select class="form-control" name="sex" id="sex" placeholder="płeć" value="${sex}">
        <option name="male" value="MALE">Mężczyzna</option>
        <option name="female" value="FEMALE">Kobieta</option>
      </select>
    </div>

    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="email" value="${email}">
    </div>

    <div class="form-group">
      <label for="preferedSpecialization">Jaka specjalizacja lekarza?</label>
      <select class="form-control" name="preferedSpecialization" id="preferedSpecialization" placeholder="Jaka specjalizacja lekarza?" value="${preferedSpecialization}">
        <option value="dentist">dentysta</option>
        <option value="internist">internista</option>
        <option value="laryngologist">laryngolog</option>
        <option value="urologist">urolog</option>
      </select>
    </div>

    <div class="form-group">
      <label for="preferedDay">Jaki dzień tygodnia?</label>
      <select class="form-control" name="preferedDay" id="preferedDay" placeholder="Jaki dzień tygodnia?" value="${preferedDay}">
        <option name="monday" value="monday">Poniedziałek</option>
        <option name="tuesday" value="tuesday">Wtorek</option>
        <option name="wednesday" value="wednesday">Środa</option>
        <option name="thursday" value="thursday">Czwartek</option>
        <option name="friday" value="friday">Piątek</option>
      </select>
    </div>

    <p><input class="btn btn-default" type="submit" value="Submit"></p>

  </form>

  <jsp:include page="partials/footer.jsp" />
</div>
</body>
</html>
