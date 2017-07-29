<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="partials/meta.jsp"/>
</head>
<body id="start" data-spy="scroll" data-target=".navbar" data-offset="60">

<jsp:include page="partials/navbar.jsp"/>

<div class="container">
    <br><br><br>
    <div class="panel-group">
        <div class="panel panel-panel-info">
            <div><h1><strong>ICD-9 Classification</strong></h1></div>
        </div>
    </div>

    <div class="panel-group" id="accordion">
        <c:forEach var="section" items="${classification.sections}">
            <tags:sectionView section="${section}"/>
        </c:forEach>
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
