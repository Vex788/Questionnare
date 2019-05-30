package www.questionnaire.com.ua;

import usersData.UsersData;

import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    UsersData usersData = new UsersData();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        usersData.loadData();

        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String dir = "index.jsp";
        boolean bl = false;
        boolean bp = false;

        for (UsersData.User user: usersData.usersList) {
            if (user.Login.equals(login)) {
                bl = true;
                if (user.Password.equals(password)) {
                    bp = true;
                    session.setAttribute("user_login", login);
                    dir = "questions.jsp";
                    break;
                }
            }
        }

        if (!bl) {
            session.setAttribute("login_exists", "false");
        } else {
            session.setAttribute("login_exists", null);
        }
        if (!bp) {
            session.setAttribute("password_bool", "false");
        } else {
            session.setAttribute("password_bool", null);
        }

        response.sendRedirect(dir);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }
}
