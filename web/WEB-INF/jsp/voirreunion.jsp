<%-- 
    Document   : voirreunion
    Created on : May 14, 2020, 6:31:03 p.m.
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
            <form:form method="post" modelAttribute="reunionForm" action="VoirReunion">
                <form:label path="titre">Titre de la reunion</form:label>
                <form:select class="form-control" path="titre">
                    <c:forEach items="${reunions}" var="uneReunion">
                        <form:option value="${uneReunion.getTitre()}"></form:option>
                    </c:forEach>
                </form:select> 
                <input type="submit" class="btn btn-primary" value="Voir les informations de la reunion"/>
            </form:form>
            <p>
                <span class="message">${erreur}</span>
            </p>
            <p>
                <span class="message">${message}</span>
            </p>
            <c:if test="${!empty reunion}">

                <c:if test="${sessionScope.role == 2}">
                    <h2><a href="changerreunion/?nom=${reunion.getTitre()}"><c:out value="Modifier la reunion : ${reunion.getTitre()}" /></a></h2>
                    </c:if>
                    <c:if test="${sessionScope.role == 1}">
                    <h2> ${reunion.getTitre()}</h2>
                </c:if>
                <p>
                    ${reunion.getFormattedDate()}<br>
                    ${reunion.getFormattedBoolean()}<br>
                    ${reunion.getStatus()}<br>
                    La duree de cette reunion sera de: ${reunion.getDuree()} minutes!
                </p>
                <c:if test="${reunion.isReunionOuverte()}">
                    <br><a href="annuler/?id=${reunion.getId()}"><c:out value="Annuler la réunion?" /> <i class="glyphicon glyphicon-minus"> (Elle sera supprimée)</i></a>
                    <br><a href="ajouterpoint/?id=${reunion.getId()}">Ajouter un point d'ordre? <i class="glyphicon glyphicon-plus"></i></a>
                    <br><a href="inserercontenu/?id=${reunion.getId()}"><c:out value="Modifier un point d'ordre?" /></a>
                </c:if>
                <br><a href="voircompterendu/?id=${reunion.getId()}"><c:out value="Voir le compte rendu de la reunion : ${reunion.getTitre()} ?" /></a>
            </c:if>
        </div>
    </body>
</html>
