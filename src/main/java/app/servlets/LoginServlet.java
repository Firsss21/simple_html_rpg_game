package app.servlets;

import app.models.Player;
import app.models.User;
import app.dao.PlayerDao;
import app.dao.UsersDao;
import app.utils.Authentication;
import app.utils.Md5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // вытаскиваем из запроса имя пользователя и его пароль
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        Player player;

        if (!validInput(name, password))
        {
            req.setAttribute("message", " Требуется пароль длинее 5 символов, логин длинее 5 символов, разрешены цифры и английские буквы");
            req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        if (Authentication.userExist(name)) {
            player = Authentication.loginAndGetPlayer(name, password);

            if (player == null)  {
                req.setAttribute("message", "Неправильный пароль.");
                req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                return;
            }

            player.clientSessions.add(session);
        }
        else {
            player = Authentication.createUserAndGetPlayer(name, password);
            player.clientSessions.add(session);
        }

        session.setAttribute("player", player);

        resp.sendRedirect(req.getContextPath() + "/home");
    }

    public boolean validInput(String name, String password) {
        String regexPattern = "^[A-Za-z0-9]+$";
        if (name.length() < 4
                || password.length() < 5
                || !name.matches(regexPattern)
                || !password.matches(regexPattern))
        {
            return false;
        } else {
            return true;
        }
    }
}


