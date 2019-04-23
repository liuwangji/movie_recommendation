<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>你的私人影院</title>
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="header"
     style="background-image: url(figure/head.jpg);height: 75px">
    <div class="container">
        <div style="height: 100px; color: cornsilk"><h1>电影推荐系统</h1></div>
    </div>
</div>

<div class="floatCSS">
    <div class="display searchCenter display">
        <div class="width40">
            <form id="init" action="/init" method="get">
                <input type="submit" class="btn" value="初始化" size=100>
            </form>
        </div>
        <div class="width40">
            <form id="login" action="/login" method="get">
                <input type="submit" class="btn" value="登陆" size=100>
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