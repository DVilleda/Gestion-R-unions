<%-- 
    Document   : reunion
    Created on : May 5, 2020, 10:49:30 a.m.
    Author     : socce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/styles/bootstrap413/css/bootstrap.css" />" rel="stylesheet" type="text/css">
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="home">acceuil</a>
                </li>
                <c:if test="${empty sessionScope.username}"> 
                    <li class="nav-item">
                        <a class="nav-link" href="connection">connexion</a>
                    </li>
                </c:if>
                <c:if test="${!empty sessionScope.username}">
                    <li class="nav-item">
                        <a class="nav-link" href="calendrier">calendrier</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="dossier">dossier</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="reunion">reunion</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="deconnexion">deconnexion</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <div class="container">    
            <h1>Réunions</h1>

         
            <c:if test="${sessionScope.role == 2}">
                <span>Bonjour ${sessionScope.username} vous pouvez voir, modifier ou créer des réunions grâce aux liens ...</span><br>
                <a class="btn btn-primary" href="creerreunion">créer/modifier une réunion</a><br>
                <a class="btn btn-primary" href="VoirReunion">Voir les détails d'une réunion</a><br>
            </c:if>
            <c:if test="${sessionScope.role == 1}">
                Bonjour ${sessionScope.username} vous pouvez voir les informations des réunions ici ... <br>
                <a class="btn btn-primary" href="VoirReunion" >Voir les détails d'une réunion</a><br>
            </c:if>
        </div>
    </body>
</html>
