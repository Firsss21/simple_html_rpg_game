package app.servlets;


import app.matchmaker.FightManager;
import app.models.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("find") != null) {

            Player player = (Player) req.getSession().getAttribute("player");
            FightManager.registerPlayer(player);

            resp.sendRedirect(req.getContextPath() + "/waiting");

        } else if (req.getParameter("logout") != null) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}