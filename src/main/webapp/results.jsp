<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Результаты</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    table { border-collapse: collapse; margin-bottom: 20px; }
    th, td { border: 1px solid #ddd; padding: 8px; }
    th { background-color: #f2f2f2; }
  </style>
</head>
<body>
<h1>Результаты голосования</h1>

<h2>Лучшие исполнители:</h2>
<table>
  <tr><th>Исполнитель</th><th>Голоса</th></tr>
  <c:forEach items="${artistResults}" var="entry">
    <tr><td>${entry.key}</td><td>${entry.value}</td></tr>
  </c:forEach>
</table>

<h2>Любимые жанры:</h2>
<table>
  <tr><th>Жанр</th><th>Голоса</th></tr>
  <c:forEach items="${genreResults}" var="entry">
    <tr><td>${entry.key}</td><td>${entry.value}</td></tr>
  </c:forEach>
</table>

<h2>Информация о голосующих:</h2>
<table>
  <tr><th>Время</th><th>О себе</th></tr>
  <c:forEach items="${aboutResults}" var="entry">
    <tr><td>${entry.key}</td><td>${entry.value}</td></tr>
  </c:forEach>
</table>

<p><a href="form">Вернуться к голосованию</a></p>
</body>
</html>