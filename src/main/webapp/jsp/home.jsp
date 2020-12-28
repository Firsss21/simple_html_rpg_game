<% long time = System.currentTimeMillis(); %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.Main" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Меню</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">


</head>
<body>
<div class="decor">
    <form class="form-inner" action="${pageContext.request.contextPath}/home" method="post">
        <h4>
           <p>Имя: ${player.getName()} </p>
           <p>Рейтинг: ${player.getElo()} </p>
           <p>Здоровье: ${player.getHealthPoints()} </p>
           <p>Урон: ${player.getAttackRate()} </p>
        </h4>


        <input type="submit" name="find" value="Поиск противника" />
        <input type="submit" name="logout" value="Выйти" />
    </form>


</div>
</body>
</html>
<%
    time = System.currentTimeMillis() - time;
    out.write("<p style='position:absolute; bottom: 15px;'> db:" + Main.CONNECTIONS_CREATED + " req (total ms: "+ Main.TOTAL_CONNECTION_TIME +") (avg MS: " + Main.getAvgMs() + " )</p>");
    out.write("<p style='position:absolute; bottom: 1px;'>page: " + time + " ms</p>");
%>