<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Голосование</title>
    <jsp:include page="additional/style.jsp"></jsp:include>
</head>
<body>
<jsp:include page="additional/header.jsp"></jsp:include>
<h1>Результаты голосования</h1>

<h2>Лучшие исполнители:</h2>
<table>
    <tr>
        <th>Исполнитель</th>
        <th>Голоса</th>
    </tr>
    <c:forEach items="${artistResults}" var="entry">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>

<h2>Любимые жанры:</h2>
<table>
    <tr>
        <th>Жанр</th>
        <th>Голоса</th>
    </tr>
    <c:forEach items="${genreResults}" var="entry">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>

<h2>Информация о голосующих:</h2>
<table>
    <tr>
        <th>Время</th>
        <th>О себе</th>
    </tr>
    <c:forEach items="${aboutResults}" var="entry">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="vote">Вернуться к голосованию</a></p>
<p><a href="delete">Удалить голоса</a></p>
<jsp:include page="additional/footer.jsp"></jsp:include>
</body>
</html>