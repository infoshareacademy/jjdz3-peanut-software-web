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
            <a class="navbar-brand" href="index.html">
                <span class="glyphicon glyphicon-link"></span> PEANUT MEDICINE</a>
        </div>
        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#about">ABOUT US</a></li>
                <li><a href="#">APPOINTMENTS</a></li>
                <li><a href="#">NEW PATIENTS</a></li>
                <li><a href="#">PRESCRIPTIONS</a></li>
                <li><a href="#services">SERVICES</a></li>
                <li><a href="#staff">STAFF</a></li>
                <li><a href="#contact">CONTACT</a></li>
                <li>
                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                </li>
            </ul>
        </div>
    </div>
</nav>