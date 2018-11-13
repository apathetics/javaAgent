<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automatically -->
    <link rel="stylesheet" type="text/css" href="../../webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

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
                <li><a href="/">Home</a></li>
                <li class="active"><a href="#">Search</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="starter-template">
        <h1>Search</h1>

        <p>Please enter a response/request unique ID to view its metrics.</p>
        <form id="searchForm">
            ID: <input type="text" name="uuid" width="25%">
            <input type="button" onclick ="metricGet()" value="Submit">
        </form>

        <div style="padding-top: 15px">
        <table>
            <tr>
                <th>ID (UUID)</th>
                <th>Request Time (ms)</th>
                <th>Response Size (bytes)</th>
            </tr>
            <tr>
                <th>${id}</th>
                <th><fmt:formatNumber value="${requestTime}" maxFractionDigits="0"/></th>
                <th><fmt:formatNumber value="${responseSize}" maxFractionDigits="0"/></th>
            </tr>
        </table>
        </div>
    </div>
</div>

<script src="../../webjars/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="../../webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    var metricGet = function() {
        var searchId = document.getElementById("searchForm").elements[0].value;
        window.location.href = "/search/result/" + searchId;
    }
</script>

</body>

</html>
