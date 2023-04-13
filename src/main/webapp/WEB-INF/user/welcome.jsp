<%@ page import="org.example.service.AnimeService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Страница пользователя</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #F5F5F5;
        }

        /* Стили для аватарки */
        .profile-avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background-color: gray;
        }

        /* Стили для никнейма */
        .username {
            font-size: 24px;
            font-weight: bold;
            margin-left: 20px;
        }

        /* Стили для кнопок */
        .button {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 8px;
        }

        .button.active {
            background-color: #004C6D;
        }

        /* Стили для списков аниме */
        .anime-list {
            margin-top: 16px;
            display: none;
        }

        .anime-list.active {
            display: block;
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

        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
        }

        #add-anime-form {
            background-color: #FFFFFF;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            padding: 20px;
            width: calc(33.33% - 20px);
            margin-bottom: 30px;
            box-sizing: border-box;
            display: none;
        }

        #submit-anime {
            margin-top: 15px;
        }

        .anime-avatar {
            width: 150px;
            height: 215px;
        }

    </style>
</head>
<body>

<div class="center">
    <!-- Аватарка -->
    <div class="avatar">
        <jsp:useBean id="user" scope="request" type="org.example.entities.UserAccount"/>
        <c:set var="user" value="${user}"/>
        <img class="profile-avatar" src="${user.pictureURL}" alt="avatar">
    </div>

    <!-- Никнейм -->
    <div class="username">
        Никнейм: ${user.name}
    </div>
</div>
<!-- Кнопки для отображения списков -->
<div class="center">
    <button class="button active" data-list="all">Все</button>
    <button class="button" data-list="watching">Смотрю</button>
    <button class="button" data-list="watched">Посмотрел</button>
    <button class="button" data-list="planning">Хочу посмотреть</button>
</div>
<div class="center">
    <button class="button" id="add-anime-btn">Добавить аниме</button>
</div>
<div class="center">
    <div id="add-anime-form">
        <h2>Добавление аниме</h2>
        <form method="post" action="${pageContext.request.contextPath}/user/welcome">
            <div>
                <label for="anime-select">Название аниме:</label>
                <input type="text" id="anime-select" name="animeSelect">
            </div>
            <div>
                <label for="episodes-watched">Кол-во просмотренных эпизодов:</label>
                <input type="number" id="episodes-watched" name="episodesWatched" min="0" max="999" step="1" value="0">
            </div>
            <div>
                <label for="favorite">Любимое:</label>
                <input type="checkbox" id="favorite" name="favorite">
            </div>
            <div>
                <label for="comment">Комментарий:</label>
                <textarea id="comment" name="comment" rows="5" cols="30"></textarea>
            </div>
            <div>
                <label for="rating-select">Рейтинг:</label>
                <select id="rating-select" name="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </div>
            <div>
                <label for="status">Статус:</label>
                <select id="status" name="condition">
                    <option value="WATCHING">Смотрю</option>
                    <option value="PLANNED">Буду смотреть</option>
                    <option value="WATCHED">Посмотрел</option>
                </select>
            </div>
            <button class="button" type="submit" id="submit-anime">Добавить</button>
        </form>
    </div>
</div>
<!-- Списки аниме -->
<jsp:useBean id="animeService" class="org.example.service.impl.AnimeServiceImpl"/>
<jsp:useBean id="accountAnimeList" scope="request" type="java.util.List"/>

<div class="anime-list active" data-list="all">
    <ul>
        <c:forEach var="accountAnime" items="${accountAnimeList}">
            <li>
                <c:set var="anime" value="${animeService.getAnimeById(accountAnime.getAnimeId())}"/>
                <h2>${anime.name}</h2>
                <div class="info">
                    <span><img class="anime-avatar" src="${anime.pictureURL}" alt="animePicture"></span><br>
                    <span>Просмотрено серий:</span> ${accountAnime.numberOfEpisodesViewed}<br>
                    <span>Всего серий:</span> ${anime.countOfSeries}<br>
                    <span>Любимое:</span> ${accountAnime.favorite}<br>
                    <span>Рейтинг:</span> ${accountAnime.rating}<br>
                    <span>Состояние:</span> ${accountAnime.conditionEnum.toString()}<br>
                    <span>Жанры:</span> ${anime.genres}<br>
                    <span>Дата выхода аниме:</span> ${anime.releaseYear}<br>
                    <span>Дата добавления:</span> ${accountAnime.dateAdded}
                </div>
                <p>Комментарий: ${accountAnime.comment}</p>
            </li>
        </c:forEach>
    </ul>
</div>

<div class="anime-list" data-list="watching">
    <ul>
        <c:forEach var="accountAnime" items="${accountAnimeList}">
            <c:if test="${accountAnime.conditionEnum eq 'WATCHING' }">
                <li>
                    <c:set var="anime" value="${animeService.getAnimeById(accountAnime.animeId)}"/>
                    <h2>${anime.name}</h2>
                    <div class="info">
                        <span><img class="anime-avatar" src="${anime.pictureURL}" alt="animePicture"></span><br>
                        <span>Просмотрено серий:</span> ${accountAnime.numberOfEpisodesViewed}<br>
                        <span>Всего серий:</span> ${anime.countOfSeries}<br>
                        <span>Любимое:</span> ${accountAnime.favorite}<br>
                        <span>Рейтинг:</span> ${accountAnime.rating}<br>
                        <span>Состояние:</span> ${accountAnime.conditionEnum.toString()}<br>
                        <span>Жанры:</span> ${anime.genres}<br>
                        <span>Дата выхода аниме:</span> ${anime.releaseYear}<br>
                        <span>Дата добавления:</span> ${accountAnime.dateAdded}
                    </div>
                    <p>Комментарий: ${accountAnime.comment}</p>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>

<div class="anime-list" data-list="watched">
    <ul>
        <c:forEach var="accountAnime" items="${accountAnimeList}">
            <c:if test="${accountAnime.conditionEnum eq 'WATCHED' }">
                <li>
                    <c:set var="anime" value="${animeService.getAnimeById(accountAnime.animeId)}"/>
                    <h2>${anime.name}</h2>
                    <div class="info">
                        <span><img class="anime-avatar" src="${anime.pictureURL}" alt="animePicture"></span><br>
                        <span>Просмотрено серий:</span> ${accountAnime.numberOfEpisodesViewed}<br>
                        <span>Всего серий:</span> ${anime.countOfSeries}<br>
                        <span>Любимое:</span> ${accountAnime.favorite}<br>
                        <span>Рейтинг:</span> ${accountAnime.rating}<br>
                        <span>Состояние:</span> ${accountAnime.conditionEnum.toString()}<br>
                        <span>Жанры:</span> ${anime.genres}<br>
                        <span>Дата выхода аниме:</span> ${anime.releaseYear}<br>
                        <span>Дата добавления:</span> ${accountAnime.dateAdded}
                    </div>
                    <p>Комментарий: ${accountAnime.comment}</p>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>

<div class="anime-list" data-list="planning">
    <ul>
        <c:forEach var="accountAnime" items="${accountAnimeList}">
            <c:if test="${accountAnime.conditionEnum eq 'PLANNED' }">
                <li>
                    <c:set var="anime" value="${animeService.getAnimeById(accountAnime.animeId)}"/>
                    <h2>${anime.name}</h2>
                    <div class="info">
                        <span><img class="anime-avatar" src="${anime.pictureURL}" alt="animePicture"></span><br>
                        <span>Просмотрено серий:</span> ${accountAnime.numberOfEpisodesViewed}<br>
                        <span>Всего серий:</span> ${anime.countOfSeries}<br>
                        <span>Любимое:</span> ${accountAnime.favorite}<br>
                        <span>Рейтинг:</span> ${accountAnime.rating}<br>
                        <span>Состояние:</span> ${accountAnime.conditionEnum.toString()}<br>
                        <span>Жанры:</span> ${anime.genres}<br>
                        <span>Дата выхода аниме:</span> ${anime.releaseYear}<br>
                        <span>Дата добавления:</span> ${accountAnime.dateAdded}
                    </div>
                    <p>Комментарий: ${accountAnime.comment}</p>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>

<script>
    const buttons = document.querySelectorAll('.button');
    const animeLists = document.querySelectorAll('.anime-list');
    buttons.forEach(button => {
        button.addEventListener('click', () => {
            // Удаляем класс active у всех кнопок
            buttons.forEach(b => b.classList.remove('active'));
            // Добавляем класс active только на нажатую кнопку
            button.classList.add('active');
            // Скрываем все списки аниме
            animeLists.forEach(list => list.classList.remove('active'));
            // Отображаем только нужный список аниме
            const listName = button.getAttribute('data-list');
            const animeList = document.querySelector(".anime-list[data-list=\"" + listName + "\"]");
            animeList.classList.add('active');
        });
    });

    const addAnimeBtn = document.getElementById('add-anime-btn');
    const addAnimeForm = document.getElementById('add-anime-form');
    const submitAnime = document.getElementById('submit-anime');

    addAnimeBtn.addEventListener('click', () => {
        addAnimeForm.style.display = 'block';
    });
    submitAnime.addEventListener('click', () => {
        addAnimeForm.style.display = 'none';
    })
</script>
</body>
</html>


