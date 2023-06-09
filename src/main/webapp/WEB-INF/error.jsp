<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body class="bg-danger">
<div class="d-flex align-items-center justify-content-center h-100 ">
    <div class="d-flex flex-column bg-light  p-5">
        <h1 class="text align-self-center p-2 shadow-lg mt-5">
            <%=  request.getSession().getAttribute("error") %>
        </h1>
        <a class="align-self-center p-2 " href="${pageContext.request.contextPath}/user/welcome">
            <button class="btn btn-primary" type="button" name="button">Вернуться на главную
            </button>
        </a>

        <img src="https://i.postimg.cc/B6mhGR43/Gb-b7o-Y-r-B4.jpg" class="card-img align-content-center mt-1">
    </div>

</div>
</body>
</html>