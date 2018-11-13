<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Java Metrics</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="/search">Search</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1>Table of Gathered Metrics</h1>

    <div class="starter-template">
        <table>
            <tr>
                <th>Minimum Request Time (ms)</th>
                <th>Minimum Response Size (bytes)</th>
                <th>Maximum Request Time (ms)</th>
                <th>Maximum Response Size (bytes)</th>
                <th>Average Request Time  (ms)</th>
                <th>Average Response Size (bytes)</th>
            </tr>
            <tr>
                <th><fmt:formatNumber value="${minRequestTime}" maxFractionDigits="0"/></th>
                <th><fmt:formatNumber value="${minResponseSize}" maxFractionDigits="0"/></th>
                <th><fmt:formatNumber value="${maxRequestTime}" maxFractionDigits="0"/></th>
                <th><fmt:formatNumber value="${maxResponseSize}" maxFractionDigits="0"/></th>
                <th><fmt:formatNumber value="${averageRequestTime}" maxFractionDigits="2"/></th>
                <th><fmt:formatNumber value="${averageResponseSize}" maxFractionDigits="2"/></th>
            </tr>
        </table>
    </div>
    <div style="padding-top: 15px">
        <button type="button" onclick="gamesGet()">Sample Games GET Request</button>
    </div>
</div>

<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    var gamesGet = function() {
        $.ajax({
            type: 'GET',
            url: '/api/games',
            dataType: 'json',
            async: true,
            success: function(result) {
                console.log(result);
                location.reload(true);
            },
            error: function(jq, textStatus, errorThrown) {
                alert('FAILURE');
            }
        });
    }
</script>
</body>

</html>
