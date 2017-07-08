<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<nav class="navbar navbar-inverse navbar-fixed-top">

    <c:if test="${!logged}">
        <div class="collapse navbar-collapse"><p class="bg-danger">&nbsp;Full site functionality available after log in!</p></div>
    </c:if>

    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">
                <span class="glyphicon glyphicon-link"></span> PEANUT MEDICINE</a>
        </div>
        <div class="collapse navbar-collapse navHeaderCollapse">

            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}#about">ABOUT US</a></li>
                <li><a href="#">APPOINTMENTS</a></li>
                <li><a href="${pageContext.request.contextPath}/survey">NEW PATIENTS</a></li>
                <li><a href="${pageContext.request.contextPath}#services">SERVICES</a></li>
                <li><a href="${pageContext.request.contextPath}#staff">STAFF</a></li>
                <li><a href="${pageContext.request.contextPath}#contact">CONTACT</a></li>
                <li>
                <c:choose>
                    <c:when test="${logged}">
                        <a href="${pageContext.request.contextPath}/logout">SIGN OUT</a>
                    </c:when>
                    <c:otherwise>
                    <div id="gSignInWrapper">
                        <span class="label">Sign in:</span>
                        <div id="customBtn" class="customGPlusSignIn">
                            <span class="icon"></span>
                            <span class="buttonText">Google</span>
                        </div>
                    </div>
                    <script>startApp();</script>
                    </c:otherwise>
                </c:choose>
                    <div id="name"></div>
                </li>
                <li><a href="admin"><span class="glyphicon glyphicon-text-background"></span></a></li>
            </ul>
        </div>
    </div>
</nav>