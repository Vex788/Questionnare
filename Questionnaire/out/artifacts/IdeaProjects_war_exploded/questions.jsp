<%@ page import="usersData.UsersData" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="/js/bootstrap.js"></script>

    <title>Questionnaire</title>
</head>
<body>
<% String login = (String) session.getAttribute("user_login"); %>
<% String showAllStatistics = (String) session.getAttribute("show_all_statistics"); %>
<% String takeASurvey = (String) session.getAttribute("take_survey"); %>
<% String usersTable = (String) session.getAttribute("users_table"); %>
<% String statisticsTable = (String) session.getAttribute("users_statistics"); %>

<div class="container">
    <% if (login != null || !"".equals(login)) { %>
    <div class="header">
        <ul class="nav nav-pills pull-right">
            <% if (login == null || "".equals(login)) { %>
            <li class="active"><a href="/register">Register</a></li>
            <% } else { %>
            <li class="active"><a href="/login?a=exit">Logout</a></li>
            <% } %>
        </ul>
        <h3 class="text-muted">Questionnaire</h3>
    </div>

    <div class="jumbotron">
        <div class="row">
            <div class="center-block">
                <h3>You are logged in as: <%= login %>
                </h3>
                <br>
                <form action="/questions" method="POST">
                    <input class="btn btn-info" type="submit" name="buttonShowAllStatistics" value="Show all"/>
                    <input class="btn btn-info" type="submit" name="buttonTakeASurvey" value="Take a survey"/>
                </form>
                <% if (showAllStatistics != null && showAllStatistics.equals("true")) { %>
                <table class="table table-striped table-dark">
                    <thead>
                    <tr>
                        <th scope="col">
                        </td>
                        <th scope="col">Q1.1</th>
                        <th scope="col">Q1.2</th>
                        <th scope="col">Q1.3</th>
                        <th scope="col">Q2.1</th>
                        <th scope="col">Q2.2</th>
                        <th scope="col">Q2.3</th>
                        <th scope="col">Q3.1</th>
                        <th scope="col">Q3.2</th>
                        <th scope="col">Q3.3</th>
                    </tr>
                    </thead>
                    <%=statisticsTable%>
                </table>
                <table class="table table-striped table-dark">
                    <thread>
                        <tr>
                            <th scope="col">Logins</th>
                            <th scope="col">- Q1 -</th>
                            <th scope="col">- Q2 -</th>
                            <th scope="col">- Q3 -</th>
                        </tr>
                    </thread>
                    <%=usersTable%>
                </table>
                <% } %>
                <% if (takeASurvey != null && takeASurvey.equals("true")) { %>
                <br>
                <h3>->Test<-<br>Who are you from the Spongebob?</h3>
                <form action="/questions" method="POST">
                    <fieldset id="group1">
                        <h4>In which house do you live?</h4>
                        <h5><input type="radio" name="name1" value="1" checked>A pineapple</h5>
                        <h5><input type="radio" name="name1" value="2">Big head</h5>
                        <h5><input type="radio" name="name1" value="3">Just a rock</h5>
                    </fieldset>
                    <fieldset id="group2">
                        <h4>What form are you?</h4>
                        <h5><input type="radio" name="name2" value="1" checked>Square</h5>
                        <h5><input type="radio" name="name2" value="2">Star</h5>
                        <h5><input type="radio" name="name2" value="3">Stretch</h5>
                    </fieldset>
                    <fieldset id="group3">
                        <h4>What color are you?</h4>
                        <h5><input type="radio" name="name3" value="1" checked>Yellow</h5>
                        <h5><input type="radio" name="name3" value="2">Coral</h5>
                        <h5><input type="radio" name="name3" value="3">Gray</h5>
                    </fieldset>
                    <input class="btn btn-info" type="submit" name="submit" value="Submit"/>
                </form>
                <% } %>
            </div>
            <% } %>
        </div>
    </div>
</div>

</body>
</html>
