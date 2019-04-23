<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>你的私人影院</title>
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<link rel="stylesheet" href="assets/css/movie.css"/>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--页面头部信息--%>
<div class="header headerBackground">
    <div class="movieTitle width40 floatLeft">
        <h1>电影推荐系统</h1>
    </div>
    <%--登陆信息--%>
    <div class="width90px floatRight">
        <%--    <div style="clear: both;"></div>--%>
        <img src="figure/user.jpg" class="img-circle pull-right login">
        <p class="user">
            ${nickName}
        </p>
        <div class="clearFloat"></div>
    </div>
</div>


<div class="floatCSS">
    <%--搜索栏--%>
    <div class="search">
        <form action="/search" method="get">
            <div id="defaultParam">
                <input type="hidden" name="userId" , value=${userId}>
                <input type="hidden" name="nickName" , value=${nickName}>
            </div>
            <div class=" searchCenter">
                <input class="width400px display height30 " name="search_text" size="22" maxlength="60"
                       placeholder="搜索电影名称、电影分类"
                       autocomplete="off">
                <input type="submit" class="width100px backGround searchButton s_btn display" value="搜索">
            </div>
        </form>
        <div class="clearFloat"></div>

        <%--分类栏--%>
        <form id="form" action="/reSearch" method='get'>
            <div id="defaultUserParam">
                <input type="hidden" name="userId" , value=${userId}>
                <input type="hidden" name="nickName" , value=${nickName}>
            </div>
            <div class="searchContainer">
                <div class="tags inLine">
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="All">
                        全部类型
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag " name="tag" value="Drama">
                        戏剧
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Horror">
                        恐怖
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Adventure">
                        冒险
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Animation">
                        动物
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="children">
                        儿童
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Comedy">
                        喜剧
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Fantasy">
                        奇幻
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Romance">
                        浪漫
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Thriller">
                        惊悚
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Action">
                        动作
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="War">
                        战争
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Crime">
                        犯罪
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Mystery">
                        神秘
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Musical">
                        音乐
                    </label>
                    <label class="float80px">
                        <input type="radio" class="tag" name="tag" value="Sci-Fi">
                        科幻
                    </label>
                </div>
                <div class="clearFloat"></div>

                <div class="marginTop20">
                    <input type="radio" class="tag display marginRight20" name="type" value="count">
                    <label class="marginRight20">
                        热度排序
                    </label>
                    <input type="radio" class="tag display marginRight20" name="type" value="rate">
                    <label class="marginRight20">
                        评分排序
                    </label>
                    <input type="submit" class="backGround searchButton s_btn display" value="搜索">
                </div>
            </div>
        </form>
        <div class="clear"></div>
        <%--电影展示--%>
        <div>
            <div id="figureList" class="marginTop width85 suitMargin">
                <c:forEach items="${searchResults}" var="item">
                    <div class="figureItem">
                        <a class="item " target="_blank" href=${item.url}>
                            <div class="cover-wp" data-isnew="false" data-id="1292052">
                                <img src=${item.imgUrl} height="80%" width="100%" alt=${item.movieTitle}>
                                <p>
                                    <span class="title">${item.movieTitle}</span>
                                    <span class="year">${item.year}</span>
                                </p>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
    <%--推荐结果--%>
    <div class="recommendation">
        <div>
            <div id="recommdationList" class="floatCSS">
                <div>
                    <h3>猜你喜欢</h3>
                </div>
                <c:forEach items="${recommendationResults}" var="item">
                    <div class="recommendationFigureItem">
                        <a class="item" target="_blank" href=${item.url}>
                            <div class="cover-wp" data-isnew="false" data-id="1292052">
                                <img src=${item.imgUrl} height="80%" width="100%" alt=${item.movieTitle}>
                                <p>
                                    <span class="title">${item.movieTitle}</span>
                                    <span class="year">${item.year}</span>
                                </p>
                            </div>
                        </a>


                    </div>
                </c:forEach>

            </div>

            <div id="preference" class="marginTop">
                <div>
                    <%--                    <div class="clear"></div>--%>
                    <h3>观影记录</h3>
                    <c:forEach items="${preferenceResults}" var="item">
                        <div class="recommendationFigureItem">
                            <a class="item" target="_blank" href=${item.url}>
                                <div class="cover-wp" data-isnew="false" data-id="1292052">
                                    <img src=${item.imgUrl} height="80%" width="100%" alt=${item.movieTitle}>
                                    <p>
                                        <span class="title">${item.movieTitle}</span>
                                        <span class="year">${item.year}</span>
                                    </p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- Scripts -->
    <%--    <script src="assets/js/jquery.min.js"></script>--%>
    <%--    <script src="assets/js/jquery.dropotron.min.js"></script>--%>
    <%--    <script src="assets/js/browser.min.js"></script>--%>
    <%--    <script src="assets/js/breakpoints.min.js"></script>--%>
    <%--    <script src="assets/js/util.js"></script>--%>
    <%--    <script src="assets/js/main.js"></script>--%>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $("input[name='tag'][value='${tag}']").prop("checked", true);
        $("input[name='type'][value='${type}']").prop("checked", true);
    </script>
</body>
</html>