<%-- 
    Document   : voircompterendu
    Created on : May 24, 2020, 4:55:50 p.m.
    Author     : Danny Alexander Villeda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/styles/bootstrap413/css/bootstrap.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/styles/bootstrap-3.3.6/css/bootstrap.css" />" rel="stylesheet" type="text/css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <a href="reunion"> <h1>Retour</h1></a>
            <h1><strong>Compte-rendu de : ${reunion.getTitre()}</strong></h1>
            <h2>Voici les points d'ordre de la réunion : </h2>
            <c:forEach items="${points}" var="unPoint">
                <h2>Le point : ${unPoint.getNom()}</h2><br>
                <p>a comme contenu :<strong> ${unPoint.getDescription()}</strong></p>
            </c:forEach>

        </div>
    </body>
</html>
