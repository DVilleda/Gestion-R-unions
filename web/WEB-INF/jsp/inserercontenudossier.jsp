<%-- 
    Document   : inserercontenudossier
    Created on : May 21, 2020, 2:02:36 p.m.
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
            <ul>
                <c:forEach items="${reunion}" var="unPoint">
                    <li><a href="?nom=${unPoint.getNom()}"><c:out value="${unPoint.getNom()}" /></a></li>
                    </c:forEach>
            </ul>
            <h1>${message}</h1>
            <c:if test="${!empty idreunion}">
                <form:form method="post" modelAttribute="contenuFormcreer" action="ecriredansdossier">
                    <form:input class="form-control" path="id" value="${idreunion}" type="hidden" readonly="true"/><br>
                    <form:label path="texte">Contenu a ajouter au dossier : </form:label><br>
                    <form:textarea class="form-control" rows="5" path="texte" value="${contenu}"/><br>
                    <br><input class="btn btn-primary" type="submit" value="Ajouter du contenu au dossier"/>
                </form:form>
            </c:if>
            <h1>${error}</h1>
        </div>
    </body>
</html>
