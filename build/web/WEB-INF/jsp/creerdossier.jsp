<%-- 
    Document   : creerdossier
    Created on : May 21, 2020, 2:01:59 p.m.
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
            <c:if test="${!empty newdossier}">
                <form:form method="post" modelAttribute="dossierForm" action="CreerDossier">
                    <form:label path="titre">Titre de la reunion</form:label>
                    <form:input class="form-control" path="titre"/><br>
                    <form:label path="estActif">La dossier est actif?</form:label>
                    <form:radiobutton path="estActif" value="true"/>
                    <form:label path="estActif">Oui</form:label>          
                    <form:radiobutton path="estActif" value="false"/>
                    <form:label path="estActif">Non</form:label><br>
                        <input class="btn btn-primary" type="submit" value="Creer le dossier"/>
                </form:form>
            </c:if>
            <p>
                <span class="message">${message}</span>
            </p>

            <c:if test="${!empty dossierTrouve}">
                <form:form method="post" modelAttribute="dossierFormModif" action="ModifierDossier">
                    <form:input class="form-control" path="id" value="${dossierTrouve.getId()}" type="hidden" readonly="true"/>
                    <form:label path="titre">Titre du dossier</form:label>
                    <form:input class="form-control" path="titre" value="${dossierTrouve.getTitre()}"/><br>
                    <form:label path="estActif">La dossier est actif?</form:label>            
                    <form:radiobutton path="estActif" value="true"/>
                    <form:label path="estActif">Oui</form:label>          
                    <form:radiobutton path="estActif" value="false"/>
                    <form:label path="estActif">Non</form:label><br>
                        <input class="btn btn-primary" type="submit" value="Modifier le dossier"/>
                </form:form>
            </c:if>
        </div>
    </body>
</html>
