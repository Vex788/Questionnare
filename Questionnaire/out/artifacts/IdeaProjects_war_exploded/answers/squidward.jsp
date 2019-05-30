<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="/js/bootstrap.js"></script>

    <title>Squidward</title>
</head>
<body>
<div class="container">
    <div class="header">
        <ul class="nav nav-pills pull-right">
            <li class="active"><a href="/login?a=exit">Logout</a></li>
            <li><a href="#" onclick="history.back();">Back in the past</a></li>
        </ul>
        <h3 class="text-muted">Questionnaire</h3>
    </div>

    <div class="jumbotron">
        <div class="row">
            <div class="center-block">
                <h3>
                    You are Squidward
                </h3>
                <h4>
                    He lives in a big head.<br>
                    It can stretch.<br>
                    Gray color.
                </h4>
                <img style="box-shadow: 0px 0px 25px -12px #000000;"
                     src="https://render.fineartamerica.com/images/rendered/medium/poster/images/artworkimages/medium/2/squidward-jolly-angie.jpg"
                     class="img-fluid" alt="You are Squidward">
            </div>
        </div>
    </div>
</div>
</body>
</html>
