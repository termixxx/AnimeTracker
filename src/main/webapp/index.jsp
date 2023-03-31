<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="gn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <title>Java in Static Page Example</title>
</head>
<body>
<h1>Java in Static Page Example</h1>
<%
    String[] arr = {"What's up?", "Hello", "It's a nice day today!"};
    String greetings = arr[(int) (Math.random() * arr.length)];
%>
<p><%= greetings %>
</p>
<form method="get" action="${pageContext.request.contextPath}/signup">
    <button type="submit"> зарегистрироваться</button>
</form>
</body>