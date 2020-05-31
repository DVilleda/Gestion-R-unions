<%-- 
    Document   : calendrier
    Created on : May 5, 2020, 10:49:19 a.m.
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
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
            <h1>Bonjour ${sessionScope.username} voici vos réunions : </h1>
            <ul class="list-group">
                <c:forEach items="${reunions}" begin="0" var="uneReunion" varStatus="index">
                    <c:if test="${status[index.index].getStatus() eq 'Present'}"><li class="list-group-item list-group-item-success"> </c:if>
                    <c:if test="${status[index.index].getStatus() eq 'Absent'}"><li class="list-group-item list-group-item-danger"> </c:if>  

                        ${uneReunion.getTitre()} Sera : ${status[index.index].getStatus()}  
                        <a href="changerstatus/?&idcompte=${status[index.index].getCompte_id()}&idreunion=${status[index.index].getReunion_id()}&status=${status[index.index].getStatus()}">
                            <i class="glyphicon glyphicon-plus"></i></a></li>
                        </c:forEach>
            </ul>
        </div>
    </body>
</html>
