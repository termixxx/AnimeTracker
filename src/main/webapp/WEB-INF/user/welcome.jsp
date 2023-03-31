<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.repository.AnimeRepository" %>
<%@ page import="org.example.utils.PullConnectionBuilder" %>
<%@ page import="org.example.entities.Anime" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%
    AnimeRepository animeRepository = new AnimeRepository(new PullConnectionBuilder());
    Anime anime = animeRepository.getById(86L);
%>
<h1>Привет это ваш список аниме:</h1>
<p>
    <%= anime%>
</p>

</body>
</html>
