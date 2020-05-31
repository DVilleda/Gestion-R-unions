<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="creercompte">Créer un nouveau compte</a>
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
                    <a class="nav-link" href="deconnexion">déconnexion</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <c:if test="${!empty sessionScope.username}">
        <div class="col-xs-1 text-center">
            <p>
                Bienvenue ${sessionScope.username} vous pouver accèder aux differents onglets de l'application avec le menu du haut!
            </p>
        </div>
    </c:if>
    <c:if test="${empty sessionScope.username}">
        <div class="col-xs-1 text-center">
            <p>
                Bienvenue au site de gestion de dossier et reunions. Cette application va vous permettre de faire la gestion de réunions et de leurs points d'ordre.
                Vous allez aussi être capable de faire la gestion de dossier et d'entrer de le mettre à jour avec du nouveau contenu.
                De plus vous allez pouvoir visualiser le compte-rendu ainsi que les informations d'une réunion et le compte-rendu d'un dossier.
            </p>
        </div>
    </c:if>
</body>
</html>
