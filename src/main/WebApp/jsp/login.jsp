<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>
    <div>
        Please Login
    </div>
    <form method="post" action="/login">
        <label for="name">User name
            <input type="text" id="name" name="name">
        </label>
        <label for="password">Password
            <input type="password" id="password" name="password">
        </label>
        <input type="submit" value="Sign Up">
    </form>
</div>
</body>
</html>
