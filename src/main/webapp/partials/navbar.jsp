<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
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
                <c:choose>
                    <c:when test="${logged}">
                        <li><a href="https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080${pageContext.request.contextPath}/logout">SIGN OUT</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li><a href="admin"><span class="glyphicon glyphicon-text-background"></span></a></li>
            </ul>
        </div>

        <script>
            //google callback. This function will redirect to our login servlet
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                console.log('ID: ' + profile.getId());
                console.log('Name: ' + profile.getName());
                console.log('Image URL: ' + profile.getImageUrl());
                console.log('Email: ' + profile.getEmail());
                console.log('id_token: ' + googleUser.getAuthResponse().id_token);

                //do not post all above info to the server because that is not secure.
                //just send the id_token

                var redirectUrl = "${pageContext.request.contextPath}/login";

//                using jquery to post data dynamically
                var form = $('<form action="' + redirectUrl + '" method="post">' +
                    '<input type="text" name="id_token" value="' + googleUser.getAuthResponse().id_token + '" />' +
                    '<input type="text" name="name" value="' + profile.getName() + '" />' +
                    '<input type="text" name="imageUrl" value="' + profile.getImageUrl() + '" />' +
                    '<input type="text" name="email" value="' + profile.getEmail() + '" />' +
                    '</form>');
                $('body').append(form);
                form.submit();
            }
        </script>

    </div>
</nav>