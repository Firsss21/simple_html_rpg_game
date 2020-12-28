<% long time = System.currentTimeMillis(); %>
<%@ page import="app.Main" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
     <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <title>Ожидание</title>


    <div class="decor">
       <div class="form-inner">
           <h4 class="higher">
               <c:out value="${timeToStart == null ? 'поиск противника': 'до начала боя: '}"/>
               <c:out value="${timeToStart == null ? timeToStart : (30-timeToStart)} "/>
               <c:out value="${timeToStart == null ? timeToStart :  секунд} "/>
           </h4>

            <c:choose>
                <c:when test="${battle.player1.getName() != null}">
                    <div class="form-inner-inner">
                       <h2 class="mr_nul">Имя: ${battle.player1.getName()} </h2>
                       <h2 class="mr_nul">Рейтинг: ${battle.player1.getElo()} </h2>
                       <h2 class="mr_nul">Здоровье: ${battle.player1.getTempHp()} </h2>
                       <h2 class="mr_nul">Урон: ${battle.player1.getAttackRate()} </h2>
                    </div>
                    <div class="form-inner-inner">
                               <h2 class="mr_nul">Имя: ${battle.player2.getName()} </h2>
                               <h2 class="mr_nul">Рейтинг: ${battle.player2.getElo()} </h2>
                               <h2 class="mr_nul">Здоровье: ${battle.player2.getTempHp()} </h2>
                               <h2 class="mr_nul">Урон: ${battle.player2.getAttackRate()} </h2>
                    </div>

                </c:when>
            </c:choose>
        </div>
    </div>
</head>
<%
    response.setIntHeader("Refresh", 1);
%>

<body>

</body>
</html>
<%
    time = System.currentTimeMillis() - time;
    out.write("<p style='position:absolute; bottom: 15px;'> db:" + Main.CONNECTIONS_CREATED + " req (total ms: "+ Main.TOTAL_CONNECTION_TIME +") (avg MS: " + Main.getAvgMs() + " )</p>");
    out.write("<p style='position:absolute; bottom: 1px;'>page: " + time + " ms</p>");
%>