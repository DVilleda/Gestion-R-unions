<%-- 
    Document   : Connection
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
        <c:if test="${empty sessionScope.username}"> 
            <form:form method="post" modelAttribute="form">
                <form:label path="username">Entrer votre nom d'usager </form:label>
                <form:input path="username" /><br>
                <form:errors path="username" />
                <form:label path="password">Entrer votre mot de passe</form:label>
                <form:password path="password" /><br>
                <input type="submit" value="Connecter" />
            </form:form>
        </c:if>
        <c:if test="${!empty sessionScope.username}">
            <span>Vous êtes déjà connecté</span>
        </c:if>
        <p>
            <span class="message">${message}</span>
        </p>
    </body>
</html>
