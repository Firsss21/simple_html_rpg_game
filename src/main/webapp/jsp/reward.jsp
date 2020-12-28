<% long time = System.currentTimeMillis(); %>
<%@ page import="app.Main" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Награда</title>
     <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="decor">
    <form class="form-inner" method="post" action="/reward">
        <h3> ${battleResult} </h3>
        <input type="submit" value="В меню">
    </form>
</div>
</body>
</html>
<%
    time = System.currentTimeMillis() - time;
    out.write("<p style='position:absolute; bottom: 15px;'> db:" + Main.CONNECTIONS_CREATED + " req (total ms: "+ Main.TOTAL_CONNECTION_TIME +") (avg MS: " + Main.getAvgMs() + " )</p>");
    out.write("<p style='position:absolute; bottom: 1px;'>page: " + time + " ms</p>");
%>