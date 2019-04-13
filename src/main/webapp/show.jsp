<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>你的私人影院</title>
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="header"
     style="background-image: url(figure/head.jpg);height: 75px">
    <div class="container">
        <div style="height: 100px; color: cornsilk"><h1>电影推荐系统</h1></div>
        </div>
</div>
<div>
    <div style="clear: both;"></div>
    <img src="figure/user.jpg" style="position: absolute;right: 10px;
    top: 0;
    z-index: 1;
    height: 50px;
    width: 50px;
    text-align: center;
    line-height: 50px;" class="img-circle pull-right">
    <p style="position: absolute;right: 10px;
    top: 5px;
    text-align: center;
    line-height: 110px;font-size: 20px;color: cornsilk">
        ${nickName}
    </p>
    <div style="clear: both;"></div>
</div>

<div style="left: 10px; right: 10px">
    <div id="preference" style="float:left">
        <h3>历史偏好电影</h3>
        <table class="table table-striped table-bordered" id="prefenceTable">
            <tr>
                <td>title</td>
                <td>genres</td>
            </tr>
            <c:forEach items="${recommendationResults}" var="item">
                <tr>
                    <td>${item.movieTitle}</td>
                    <td>${item.genres}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="recommdation" style="float:right">
        <h3>猜你喜欢</h3>
        <table class="table table-striped table-bordered" id="recommdationTable">
            <tr>
                <td>title</td>
                <td>genres</td>
            </tr>
            <c:forEach items="${preferenceResults}" var="item">
                <tr>
                    <td>${item.movieTitle}</td>
                    <td>${item.genres}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div id="like" style="float:right">
    <h3>大家都在看</h3>
    <table class="table table-striped table-bordered" id="peopleLikeByLookCountTable">
        <tr>
            <td>title</td>
            <td>genres</td>
        </tr>
        <c:forEach items="${peopleLikeByLookCountResults}" var="item">
            <tr>
                <td>${item.movieTitle}</td>
                <td>${item.genres}</td>
            </tr>
        </c:forEach>
    </table>
    <table class="table table-striped table-bordered" id="peopleLikeByRatingTable">
        <tr>
            <td>title</td>
            <td>genres</td>
        </tr>
        <c:forEach items="${peopleLikeByRatingResults}" var="item">
            <tr>
                <td>${item.movieTitle}</td>
                <td>${item.genres}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>