<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="survey" items="${surveys}">

    ${survey.name}
    ${survey.surname}
</c:forEach>

</body>
</html>
