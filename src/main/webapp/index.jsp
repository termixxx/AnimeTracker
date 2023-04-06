<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="gn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>AnimeTracker</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            background-color: #f2f2f2;
            font-family: Verdana, sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            font-size: 3rem;
            margin: 0;
        }

        p {
            font-size: 1.5rem;
            margin-top: 1rem;
            margin-bottom: 3rem;
        }

        .start-btn {
            background-color: #007bff;
            color: #fff;
            padding: 1rem 2rem;
            border: none;
            border-radius: 5px;
            font-size: 1.5rem;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
        }

        .start-btn:hover {
            background-color: #0069d9;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Аниме трекер</h1>
    <p>Следите за всеми вашими любимыми аниме!</p>
    <a href="signup">
        <button class="start-btn">Начать</button>
    </a>
</div>
</body>
</html>

