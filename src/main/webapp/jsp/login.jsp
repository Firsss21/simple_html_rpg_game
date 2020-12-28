<% long time = System.currentTimeMillis(); %>
<%@ page import="app.Main" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
         <link href="/css/styles.css" rel="stylesheet" type="text/css">
          <title>Авторизация</title>
    </head>
    <body>
        <form class="decor" method="post" action="/login">

            <div class="form-inner">
            <h3>Авторизация</h3>
            <label for="name">
                <input type="text" id="name" name="name" placeholder="Никнейм">
            </label>
            <label for="password">
                <input type="password" id="password" name="password" placeholder="Пароль">
            </label>
            <p> ${message} </p>
            <input class="mr_top" type="submit" value="Войти">
            </div>
        </form>
    </body>
</html>
<%
    time = System.currentTimeMillis() - time;
    out.write("<p style='position:absolute; bottom: 15px;'> db:" + Main.CONNECTIONS_CREATED + " req (total ms: "+ Main.TOTAL_CONNECTION_TIME +") (avg MS: " + Main.getAvgMs() + " )</p>");
    out.write("<p style='position:absolute; bottom: 1px;'>page: " + time + " ms</p>");
%>