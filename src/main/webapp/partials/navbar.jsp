<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<nav class="navbar navbar-inverse navbar-fixed-top">

    <div class="collapse navbar-collapse">
        <c:choose>
            <c:when test="${logged}">
                <small id="customNavPanel" class="pull-right">
                    <span class="glyphicon glyphicon-user"></span> ${name} ${surname}</small>
            </c:when>
            <c:otherwise>
                <p class="bg-danger text-center">
                    <span class="glyphicon glyphicon-hand-right"></span>
                    Full site functionality available after log in!</p>
            </c:otherwise>
        </c:choose>
    </div>

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
                <%--<li><a href="#">APPOINTMENTS</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/survey">NEW PATIENTS</a></li>
                <c:choose>
                    <c:when test="${admin}">
                        <li><a href="admin">ADMIN PANEL</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}#services">SERVICES</a></li>
                        <li><a href="${pageContext.request.contextPath}#staff">STAFF</a></li>
                        <li><a href="${pageContext.request.contextPath}#contact">CONTACT</a></li>
                    </c:otherwise>
                </c:choose>
                <li>
                    <c:choose>
                        <c:when test="${logged}">
                            <a href="${pageContext.request.contextPath}/logout">SIGN OUT</a>
                        </c:when>
                        <c:otherwise>
                            <div id="gSignInWrapper">
                                <div id="customBtn" class="customGPlusSignIn">
                                    <span class="buttonText">Sign in with</span>
                                    <span class="icon"></span>
                                    <span class="buttonText">Google</span>
                                </div>
                            </div>
                            <script>startApp();</script>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </div>
</nav>