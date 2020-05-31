<%-- 
    Document   : dossier
    Created on : May 5, 2020, 10:49:39 a.m.
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
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                </div>
                <div class="col-sm-8 text-left"> 
                    <h1>Voir un dossier</h1>
                    <span>Voici les dossier actuels : </span>
                    <ul class="list-group">
                        <c:forEach items="${dossier}" begin="0" var="unDossier">
                            <li class="list-group-item list-group-item-info">${unDossier.getTitre()}</li>
                            </c:forEach>
                    </ul>
                    <br>
                    <c:if test="${sessionScope.role == 2}">
                        <a href="creerDossier" class="btn btn-primary">créer un dossier</a><br>
                    </c:if>
                    <a href="VoirDossier" class="btn btn-primary">Voir les détails d'un dossier</a><br>
                </div>
            </div>
        </div>
    </body>
</html>
