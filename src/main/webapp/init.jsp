<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>你的私人影院</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="assets/css/movie.css"/>
</head>
<body class="backGround">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<%--页面头部信息--%>
<div class="header headerBackground">

</div>
<div class="floatCSS">
    <div class=" indexMargin">
        <div class="width100px floatLeft">
            <form id="init" height="100px" width="200px" action="/init" method="get">
                <input type="submit" class=" btnSize" value="初始化" size=100>
            </form>
        </div>
        <div class="width100px floatLeft">
            <form id="register" height="100px" width="200px" action="/register" method="get">
                <input type="submit" class=" btnSize" value="注册" size=100>
            </form>
        </div>
        <div class="width100px floatRight">
            <form id="login" height="50px" width="100px" action="/login" method="get">
                <input type="submit" class=" btnSize" value="登陆" size=100>
            </form>
        </div>
    </div>
</div>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>