<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Голосование</title>
</head>
<body>
<h1>Удаление Голосов</h1>
<%-- Отображение ошибок --%>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<form action="delete" method="post">
    <input type="checkbox" id="deleteAll" name="deleteAll" value="true">
    <label for="deleteAll">Удалить все голоса</label>
    <button type="submit">Подтвердить</button>

    <h2>Выберите какой голос удалить:</h2>
    <table>
        <c:forEach items="${aboutDelete}" var="entry">
            <tr>
                <td>
                    <input type="checkbox" name="aboutList" value="${entry.value} (${entry.key})"
                           id="chk_${entry.key}">
                    <label for="chk_${entry.key}">
                            ${entry.value} (${entry.key})
                    </label>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Голосовать">
</form>

<p><a href="vote">Вернуться к голосованию</a></p>

</body>
</html>
