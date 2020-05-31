<%-- 
    Document   : contenudossier
    Created on : May 21, 2020, 1:00:26 p.m.
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
    </head>
    <body>
        <div class="container">
            <a href="dossier"> <h1>Retour</h1></a>
            <form:form method="post" modelAttribute="dossierForm" action="VoirDossier">
                <form:label path="titre">Titre du dossier</form:label>
                <form:select class="form-control" path="titre">
                    <c:forEach items="${dossiers}" var="unDossier">
                        <form:option value="${unDossier.getTitre()}"></form:option>
                    </c:forEach>
                </form:select> 
                <input type="submit" class="btn btn-primary" value="Voir les informations du dossier"/>
            </form:form>
            <p>
                <span class="message">${message}</span>
            </p>
            <c:if test="${!empty dossier}">
                <ul class="list-group">
                    <c:if test="${sessionScope.role == 2}">
                        <li class="list-group-item list-group-item-warning"><a href="changerdossier/?id=${dossier.getId()}"><c:out value="Modifier le dossier : ${dossier.getTitre()}" /></a></li>
                        </c:if>

                    <c:if test="${dossier.getEstActif()}">
                        <li class="list-group-item list-group-item-info">Ce dossier est encore actif</li>
                        </c:if>
                        <c:if test="${!dossier.getEstActif()}">
                        <li class="list-group-item list-group-item-danger">Ce dossier à été désactivé</li>
                        </c:if>

                    <c:if test="${dossier.getEstActif()}">
                        <li class="list-group-item list-group-item-success"><a href="ajoutercontenu/?id=${dossier.getId()}"><c:out value="Ajouter du contenu au dossier" /></a></li>
                        </c:if>
                </ul>
                <br><h3> Voici le contenu inscrit dans le dossier : "${dossier.getTitre()}" en ordre de l'ajout plus recent</h3><br>
                <c:forEach items="${listecontenu}" var="unContenu">
                    <h4>${unContenu.getFormattedDate()}</h4> <p>${unContenu.getContenu()}</p>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
