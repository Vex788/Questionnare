package www.questionnaire.com.ua;

import usersData.UsersData;

import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected UsersData usersData = new UsersData();

    private boolean loginExists(String login) { // check on the same login in user table
        for (UsersData.User user : usersData.usersList) {
            if (user.Login.equals(login)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str) ? true : false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repPassword = request.getParameter("repeatPassword");
        String dir = "register.jsp";
        boolean bl = false;
        boolean bp = false;

        usersData.loadData();

        if (!loginExists(login) && !isNullOrEmpty(login)) {
            bl = false;
            if (password.equals(repPassword) && !isNullOrEmpty(password) && !isNullOrEmpty(repPassword)) {
                bp = true;
                UsersData.User user = new UsersData.User(); // add new user

                user.Login = login;
                user.Password = password;

                usersData.usersList.add(user);
                usersData.saveData();
                session.setAttribute("user_login", login);

                System.out.println("New user added");

                dir = "questions.jsp";
            } else {
                bp = true;
            }
        } else {
            bl = true;
        }
        // check
        if (bl) {
            session.setAttribute("login_exist", "true");
        } else {
            session.setAttribute("login_exist", null);
        }
        if (bp) {
            session.setAttribute("pass_equals", "false");
        } else {
            session.setAttribute("pass_equals", null);
        }

        response.sendRedirect(dir);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute("user_login");
            session.removeAttribute("pass_equals");
        }

        response.sendRedirect("register.jsp");
    }
}
