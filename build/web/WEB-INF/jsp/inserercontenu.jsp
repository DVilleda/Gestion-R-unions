<%-- 
    Document   : inserercontenu
    Created on : May 14, 2020, 6:38:34 p.m.
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
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <a href="VoirReunion"> <h1>Retour</h1></a>
            <c:if test="${empty idreunion}">
                <c:forEach items="${reunion}" var="unPoint">
                    <h2><a href="?nom=${unPoint.getNom()}"><c:out value="${unPoint.getNom()}" /></a>
                        <a href="deletepoint/?id=${unPoint.getId()}"><i class="glyphicon glyphicon-minus-sign"></i></a></h2><br>
                        </c:forEach>
                    </c:if>

            <h1>${message}</h1>

            <c:if test="${!empty message}">
                <p>
                    <span>Contenu actuel du point : ${contenu}</span>
                </p>
                <form:form method="post" modelAttribute="contenuForm" action="InsererContenu">
                    <form:label path="nom">Nom du point: </form:label>
                    <form:input class="form-control" path="nom" value="${message}" readonly="true"/><br>
                    <form:label path="texte">Nouveau contenu du point : </form:label><br>
                    <form:textarea class="form-control" rows="5" path="texte" value="${contenu}"/><br>
                    <br><input class="btn btn-primary" type="submit" value="Modifier le point d'ordre"/>
                </form:form>
            </c:if>
            <c:if test="${!empty idreunion}">
                <form:form method="post" modelAttribute="contenuFormcreer" action="CreerContenu">
                    <form:input class="form-control" path="id" value="${idreunion}" type="hidden" readonly="true"/><br>
                    <form:label path="nom">Nom du point: </form:label>
                    <form:input class="form-control" path="nom"/><br>
                    <form:label path="texte">Contenu du point : </form:label><br>
                    <form:textarea class="form-control" rows="5" path="texte" value="${contenu}"/><br>
                    <br><input class="btn btn-primary" type="submit" value="Creer le point d'ordre"/>
                </form:form>
            </c:if>
            <h1>${error}</h1>
        </div>
    </body>
</html>
