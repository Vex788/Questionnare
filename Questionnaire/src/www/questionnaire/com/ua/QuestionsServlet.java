package www.questionnaire.com.ua;

import usersData.UsersData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QuestionsServlet extends javax.servlet.http.HttpServlet {
    protected UsersData usersData = new UsersData();

    protected String getAllStatistics() {
        int q11 = 0, q12 = 0, q13 = 0;
        int q21 = 0, q22 = 0, q23 = 0;
        int q31 = 0, q32 = 0, q33 = 0;

        for (UsersData.User user : usersData.usersList) {
            switch (user.Q1) { // question 1
                case 1:
                    q11++;
                    break;
                case 2:
                    q12++;
                    break;
                case 3:
                    q13++;
                    break;
                default:
                    break;
            }
            switch (user.Q2) { // question 2
                case 1:
                    q21++;
                    break;
                case 2:
                    q22++;
                    break;
                case 3:
                    q23++;
                    break;
                default:
                    break;
            }
            switch (user.Q3) { // question 3
                case 1:
                    q31++;
                    break;
                case 2:
                    q32++;
                    break;
                case 3:
                    q33++;
                    break;
                default:
                    break;
            }
        }

        return String.format("<tr scope=\"col\">\r\n" +
                "                    <td>Statistics</td>\r\n" +
                "                    <td>%1$s</td>\r\n" +
                "                    <td>%2$s</td>\r\n" +
                "                    <td>%3$s</td>\r\n" +
                "                    <td>%4$s</td>\r\n" +
                "                    <td>%5$s</td>\r\n" +
                "                    <td>%6$s</td>\r\n" +
                "                    <td>%7$s</td>\r\n" +
                "                    <td>%8$s</td>\r\n" +
                "                    <td>%9$s</td>\r\n" +
                "                </tr>\r\n",
                q11, q12, q13, q21, q22, q23, q31, q32, q33);
    }

    protected int getUserIndex(String login) {
        for (int i = 0; i < usersData.usersList.size(); i++) {
            if (usersData.usersList.get(i).Login.equals(login)) {
                return i;
            }
        }

        return -1;
    }

    protected void closeStatistics(HttpSession session) {
        if (session.getAttribute("show_all_statistics") != null
                && session.getAttribute("show_all_statistics").equals("true")) {
            session.setAttribute("show_all_statistics", "false");
        }
    }

    protected void closeSurvey(HttpSession session) {
        if (session.getAttribute("take_survey") != null
                && session.getAttribute("take_survey").equals("true")) {
            session.setAttribute("take_survey", "false");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String dir = "questions.jsp";
        HttpSession session = request.getSession(true);

        if (request.getParameter("buttonShowAllStatistics") != null) { // show all statistics
            closeSurvey(session);

            if (session.getAttribute("show_all_statistics") != null
                    && session.getAttribute("show_all_statistics").equals("true")) {
                session.setAttribute("show_all_statistics", "false");
            } else {
                session.setAttribute("show_all_statistics", "true");
                usersData.loadData();
            }
            // build user table
            StringBuilder table = new StringBuilder();
            for (UsersData.User user : usersData.usersList) {
                if (user.Login.equals(session.getAttribute("user_login"))) {
                    table.append("<tr scope=\"col\" style='background: #0bead8;'>\n" +
                            "                    <td>" + user.Login + "</td>\n" +
                            "                    <td>" + user.Q1 + "</td>\n" +
                            "                    <td>" + user.Q2 + "</td>\n" +
                            "                    <td>" + user.Q3 + "</td>\n" +
                            "                </tr>");
                } else {
                    table.append("<tr scope=\"col\">\n" +
                            "                    <td>" + user.Login + "</td>\n" +
                            "                    <td>" + user.Q1 + "</td>\n" +
                            "                    <td>" + user.Q2 + "</td>\n" +
                            "                    <td>" + user.Q3 + "</td>\n" +
                            "                </tr>");
                }
            }

            session.setAttribute("users_statistics", getAllStatistics());
            session.setAttribute("users_table", table.toString());
        } else if (request.getParameter("buttonTakeASurvey") != null) { // show a survey
            closeStatistics(session);
            // show survey
            if (session.getAttribute("take_survey") != null
                    && session.getAttribute("take_survey").equals("true")) {
                session.setAttribute("take_survey", "false");
            } else {
                session.setAttribute("take_survey", "true");
            }
        } else if (request.getParameter("submit") != null) { // submit survey
            // close statistics
            closeStatistics(session);

            usersData.loadData();

            int index = getUserIndex((String) session.getAttribute("user_login")); // get current user index in user table

            if (index > 0) {
                try {
                    usersData.usersList.get(index).Q1 = Integer.parseInt(request.getParameter("name1"));
                    usersData.usersList.get(index).Q2 = Integer.parseInt(request.getParameter("name2"));
                    usersData.usersList.get(index).Q3 = Integer.parseInt(request.getParameter("name3"));

                    usersData.saveData();
                    // it's a brain, answer on question
                    if (usersData.usersList.get(index).Q1 == 3 &&
                        usersData.usersList.get(index).Q2 == 2 &&
                        usersData.usersList.get(index).Q3 == 2) {
                        dir = "/answers/patrick.jsp";
                    } else if (usersData.usersList.get(index).Q1 == 1 &&
                            usersData.usersList.get(index).Q2 == 1 &&
                            usersData.usersList.get(index).Q3 == 1) {
                        dir = "/answers/spongebob.jsp";
                    } else if (usersData.usersList.get(index).Q1 == 2 &&
                            usersData.usersList.get(index).Q2 == 3 &&
                            usersData.usersList.get(index).Q3 == 3) {
                        dir = "/answers/squidward.jsp";
                    } else {
                        dir = "/answers/undefined.jsp";
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                session.setAttribute("take_survey", "false");
                session.setAttribute("show_all_statistics", "false");
            }
        }

        response.sendRedirect(dir);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);

        response.sendRedirect("questions.jsp");
    }
}
