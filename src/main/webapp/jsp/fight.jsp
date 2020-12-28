<% long startTime = System.currentTimeMillis(); %>

<% long time = System.currentTimeMillis(); %>
<%@ page import="app.Main" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
     <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <title>Сражение!</title>

    <div class="decor">
        <div class="form-inner">

                <div class="form-inner-inner">
                    <p>${battle.player1.getName()} </p>
                    <p> ${battle.player1.getAttackRate()} <img src="/img/crit.png"/>, ${battle.player1.getTempHp()} <img src="/img/healorb.png"/> </p>
                    <progress max=${battle.player1.getHealthPoints()} value=${battle.player1.getTempHp()}></progress>
                </div>
                <div class="form-inner-inner">
                    <p>${battle.player2.getName()} </p>
                    <p> ${battle.player2.getAttackRate()} <img src="/img/crit.png"/>, ${battle.player2.getTempHp()} <img src="/img/healorb.png"/> </p>
                    <progress max=${battle.player2.getHealthPoints()} value=${battle.player2.getTempHp()}></progress>
                </div>

                <form method="post" action="/fight">
                         <input type="submit" value="Attack">
                </form>

                <div class="form-inner-inner">
                    <h2> Лог боя: </h2>
                    <c:forEach items="${battle.battle_log}" var="log">
                                 ${log} <br>
                    </c:forEach>
                </div>
        </div>
    </div>
</head>

<body>
<%
                response.setIntHeader("Refresh", 1);
            %>
</body>
</html>
<%
    time = System.currentTimeMillis() - time;
    out.write("<p style='position:absolute; bottom: 15px;'> db:" + Main.CONNECTIONS_CREATED + " req (total ms: "+ Main.TOTAL_CONNECTION_TIME +") (avg MS: " + Main.getAvgMs() + " )</p>");
    out.write("<p style='position:absolute; bottom: 1px;'>page: " + time + " ms</p>");
%>