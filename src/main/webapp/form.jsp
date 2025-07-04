<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Голосование</title>
    <jsp:include page="additional/style.jsp"></jsp:include>
    <style>
        .error {
            color: red;
            margin-bottom: 15px;
            padding: 10px;
            background-color: #ffeeee;
            border: 1px solid #ff9999;
        }

        .section {
            margin-bottom: 20px;
        }

        input[type="radio"], input[type="checkbox"] {
            margin-right: 8px;
        }

        label {
            display: block;
            margin: 5px 0;
        }

    </style>
</head>
<body>
<jsp:include page="additional/header.jsp"></jsp:include>
<h1>Онлайн-голосование</h1>

<%-- Отображение ошибок --%>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<form action="vote" method="POST">
    <div class="section">
        <h2>Любимый исполнитель:</h2>
        <c:forEach items="${artists}" var="artist">
            <label>
                <input type="radio" name="artist" value="${artist}">
                    ${artist}
            </label>
        </c:forEach>
    </div>

    <div class="section">
        <h2>Любимые жанры (выберите 3-5):</h2>
        <div class="genre-list">
            <c:forEach items="${genres}" var="genre">
                <label>
                    <input type="checkbox" name="genre" value="${genre}">
                        ${genre}
                </label>
            </c:forEach>
        </div>
    </div>

    <div class="section">
        <h2>О себе:</h2>
        <textarea name="about" rows="4" cols="50" placeholder="Расскажите немного о себе..."></textarea>
    </div>

    <input type="submit" value="Голосовать">
</form>
<jsp:include page="additional/footer.jsp"></jsp:include>
</body>
</html>
