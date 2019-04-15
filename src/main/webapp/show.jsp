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
<%--页面头部信息--%>
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

<%--搜索栏--%>
<div class="nav-search">
    <center>
        <form action="/search" method="get">
            <fieldset>

                <div class="inp">搜索：<input id="inp-query" name="search_text" size="22" maxlength="60"
                                           placeholder="搜索电影、电视剧、综艺、影人" autocomplete="off"></div>
                <div class="inp-btn"><input type="submit" value="搜索"></div>
                <input type="hidden" name="cat" value="1002">
            </fieldset>
        </form>
    </center>
</div>
<%--分类栏--%>
<div>
    <h1>选电影</h1>
    <div class="tags">
        <div class="tag-list">
            <label class="activate">
                热门
                <input type="radio" name="tag" value="热门" checked="checked">
            </label>
            <label>
                最新
                <input type="radio" name="tag" value="最新">
            </label>
            <label>
                经典
                <input type="radio" name="tag" value="经典">
            </label>
            <label>
                动作
                <input type="radio" name="tag" value="动作">
            </label>
            <label>
                喜剧
                <input type="radio" name="tag" value="喜剧">
            </label>
            <label>
                爱情
                <input type="radio" name="tag" value="爱情">
            </label>
            <label>
                科幻
                <input type="radio" name="tag" value="科幻">
            </label>
            <label>
                悬疑
                <input type="radio" name="tag" value="悬疑">
            </label>
            <label>
                恐怖
                <input type="radio" name="tag" value="恐怖">
            </label>
            <label>
                文艺
                <input type="radio" name="tag" value="文艺">
            </label>
        </div>
        <div class="custom-frm" data-type="tag">
            <input type="hidden">
            <button>确定</button>
        </div>
        <div class="tool">
            <div class="sort">
                <label>
                    <input type="radio" name="sort" value="recommend" checked="checked">按热度排序
                </label>
                <label>
                    <input type="radio" name="sort" value="rank">按评价排序
                </label>
            </div>
            <input type="hidden" name="page_limit" value="20">
            <input type="hidden" name="page_start" value="0">
        </div>

    </div>
</div>
<%--电影展示--%>
<div style="left: 10px; right: 10px">
    <div id="show">
        <div>
            <h3>按热度排序</h3>
            <div class="list">
                <div id="test" style="width: 60%">
                    <c:forEach items="${peopleLikeByLookCountResults}" var="item">
                        <div style="width: 11%">
                            <a class="item" target="_blank"
                               href="https://movie.douban.com/subject/1292052/?tag=豆瓣高分${item.movieTitle};from=gaia_video">

                                <div class="cover-wp" data-isnew="false" data-id="1292052">
                                    <img src="https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg"
                                         alt=${item.movieTitle}
                                                 data-x="2000" data-y="2963">
                                </div>
                                <p>
                                        ${item.movieTitle}
                                    <strong>item.rating</strong>
                                </p>
                            </a>
                        </div>
                    </c:forEach>
                </div>


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
            </div>

            <div style="width: 50%">
                <h3>按评分排序</h3>
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


    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>