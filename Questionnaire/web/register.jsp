<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="/js/bootstrap.js"></script>

    <title>Register</title>
</head>
<body>
<% String login_exist = (String) session.getAttribute("login_exist"); %>
<% String pass_equals = (String) session.getAttribute("pass_equals"); %>

<div class="container">
    <div class="header">
        <ul class="nav nav-pills pull-right">
            <li class="active"><a href="/login">Login</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
        <h3 class="text-muted">Questionnaire</h3>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="jumbotron center-block">
        <form class="form-horizontal" action="/register" method="POST">
            <div class="center-block">
                <div class="col-md-2"></div>
                <div class="form-group">
                    <% if (login_exist == null || "".equals(login_exist)) { %>
                    <label for="inputType" class="col-sm-2 control-label">Login</label>
                    <% } else { %>
                    <label style="color: red;" for="inputType" class="col-sm-2 control-label">Login</label>
                    <% } %>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="login" placeholder="Login">
                    </div>
                    <div class="col-sm-3">
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="form-group">
                    <label for="inputType" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <div class="col-sm-3">
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="form-group">
                    <% if (pass_equals == null || "".equals(pass_equals)) { %>
                    <label for="inputType" class="col-sm-2 control-label">Repeat password</label>
                    <% } else { %>
                    <label style="color: red;" for="inputType" class="col-sm-2 control-label">Repeat password</label>
                    <% } %>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" name="repeatPassword" placeholder="Repeat password">
                    </div>
                    <div class="col-sm-3">
                    </div>
                </div>
                <br>
                <br>
                <div class="col-md-2"></div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input class="btn btn-success btn-xs" type="submit" value="Register"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
