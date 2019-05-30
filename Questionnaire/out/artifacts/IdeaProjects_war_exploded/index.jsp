<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="/js/bootstrap.js"></script>

    <title>Login</title>
</head>
<body>
<% String login = (String) session.getAttribute("user_login"); %>
<% String loginExists = (String) session.getAttribute("login_exists"); %>
<% String passwordFound = (String) session.getAttribute("password_bool"); %>

<div class="container">
    <div class="header">
        <ul class="nav nav-pills pull-right">
            <% if (login == null || "".equals(login)) { %>
            <li class="active"><a href="/register">Register</a></li>
            <% } else { %>
            <li class="active"><a href="/login?a=exit">Logout</a></li>
            <% } %>
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
    <div class="jumbotron">
        <div class="row">
            <div class="center-block">
                <% if (login == null || "".equals(login)) { %>
                <form class="form-horizontal" action="/login" method="POST">
                    <div class="center-block">
                        <div class="col-md-2"></div>
                        <div class="form-group">
                            <% if (loginExists == null || "".equals(loginExists)) { %>
                            <label for="inputType" class="col-sm-2 control-label">Login</label>
                            <% } else { %>
                            <label style="color: red;" for="inputType" class="col-sm-2 control-label">Login</label>
                            <% } %>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="login" placeholder="Login">
                            </div>
                        </div>
                        <div class="col-md-2"></div>
                        <div class="form-group">
                            <% if (passwordFound == null || "".equals(passwordFound)) { %>
                            <label for="inputType" class="col-sm-2 control-label">Password</label>
                            <% } else { %>
                            <label style="color: red;" for="inputType" class="col-sm-2 control-label">Password</label>
                            <% } %>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" name="password" placeholder="Password">
                            </div>
                        </div>
                        <br>
                        <div class="col-md-2"></div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input class="btn btn-success btn-xs" type="submit" value="LogIn"/>
                            </div>
                        </div>
                    </div>
                </form>
                <% } %>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
