<%-- 
    Document   : changerreunion
    Created on : May 14, 2020, 6:30:54 p.m.
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
            <a href="reunion"> <h1>Retour</h1></a>
            <c:if test="${!empty Creer}">
                <form:form method="post" modelAttribute="reunionForm" action="CreerReunion">
                    <form:label path="titre">Titre de la reunion</form:label>
                    <form:input class="form-control" path="titre"/><br>
                    <form:label path="date">Date de la reunion</form:label>
                    <form:input class="form-control" type="date" path ="date"/><br>
                    <form:label path="heure">Heure de la reunion</form:label>
                    <form:input class="form-control" type="number" path ="heure"/><br>
                    <form:errors path="heure" />
                    <form:label path="minute">Minute de la reunion</form:label>
                    <form:input class="form-control" type="number" path ="minute"/><br>
                    <form:errors path="minute" />
                    <form:label path="duree">Duree de la reunion</form:label>
                    <form:input class="form-control" type="number" path ="duree"/><br>
                    <form:label path="reunion_ouverte">La reunion est ouverte?</form:label>            
                    <form:radiobutton path="reunion_ouverte" value="true"/>
                    <form:label path="reunion_ouverte">Oui</form:label>          
                    <form:radiobutton path="reunion_ouverte" value="false"/>
                    <form:label path="reunion_ouverte">Non</form:label><br>
                        <input class="btn btn-primary" type="submit" value="Creer la reunion"/>
                </form:form>
            </c:if>
            <p>
                <span class="message">${message}</span>
            </p>

            <c:if test="${!empty reunionTrouve}">
                <form:form method="post" modelAttribute="reunionFormModif" action="ModifierReunion">
                    <form:label path="titre">Titre de la reunion</form:label>
                    <form:input class="form-control" path="titre" value="${reunionTrouve.getTitre()}" readonly="true"/><br>
                    <form:label path="date">Date de la reunion</form:label>
                    <form:input class="form-control" type="date" path ="date" value="${reunionTrouve.getDate().toLocalDate()}"/><br>
                    <form:label path="heure">Heure de la reunion</form:label>
                    <form:input class="form-control" path ="heure" value="${reunionTrouve.getDate().getHour()}"/><br>
                    <form:label path="minute">Minute de la reunion</form:label>
                    <form:input class="form-control" path ="minute" value="${reunionTrouve.getDate().getMinute()}"/><br>
                    <form:label path="duree">Duree de la reunion</form:label>
                    <form:input class="form-control" path ="duree" value="${reunionTrouve.getDuree()}" /><br>
                    <form:label path="reunion_ouverte">La reunion est ouverte?</form:label>            
                    <form:radiobutton path="reunion_ouverte" value="true"/>
                    <form:label path="reunion_ouverte">Oui</form:label>          
                    <form:radiobutton path="reunion_ouverte" value="false"/>
                    <form:label path="reunion_ouverte">Non</form:label><br>
                        <input class="btn btn-primary" type="submit" value="Modifier la reunion"/>
                </form:form>
            </c:if>
        </div>
    </body>
</html>
