<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список аниме</title>
    <style>
        body {
            font-family: Verdana, sans-serif;
            background-color: #F5F5F5;
        }

        h1 {
            text-align: center;
            margin-top: 50px;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            max-width: 1200px;
            margin: 50px auto;
        }

        li {
            background-color: #FFFFFF;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            padding: 20px;
            width: calc(33.33% - 20px);
            margin-bottom: 30px;
            box-sizing: border-box;
        }

        h2 {
            font-size: 20px;
            margin-top: 0;
            margin-bottom: 10px;
        }

        .info {
            font-size: 14px;
            color: #999999;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            line-height: 1.5;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<h1>Список аниме</h1>
<ul>
    <jsp:useBean id="animeList" scope="request" type="java.util.List"/>
    <c:forEach var="anime" items="${animeList}">
        <li>
            <h2>${anime.getName()}</h2>
            <div class="info">
                <span>Количество серий:</span> ${anime.getCountOfSeries()}<br>
                <span>Жанры:</span> ${anime.getGenres()}<br>
                <span>Дата выхода:</span> ${anime.getReleaseYear()}
            </div>
            <p>${anime.getDescription()}</p>
        </li>
    </c:forEach>
</ul>
</body>
</html>


