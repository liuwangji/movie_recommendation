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

<div class="indexMargin">
    <center>
        <form id="form" action="/register" method='get'>
            <p>
                <input class="fontSize20px width200px " type="text" name="userId" placeholder=" 用 户 Id">
            </p>
            <p>
                <input class="fontSize20px width200px " type="text" name="nickName" placeholder=" 昵 称 ">
            </p>
            <p>
                <input class="fontSize20px width200px" type="text" name="password" placeholder=" 密 码">
            </p>
            <input type="submit" class="width200px backGround searchButton s_btn display" value=" 注  册 ">
            <div></div>
        </form>
    </center>
</div>

<%--</center>--%>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>