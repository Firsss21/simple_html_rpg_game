package app.servlets;

import app.matchmaker.Battle;
import app.matchmaker.FightManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet("/fight")
public class FightServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("battleResult") != null)
            resp.sendRedirect(req.getContextPath() + "/reward");
        else
            req.getRequestDispatcher("/jsp/fight.jsp").forward(req, resp);

        resp.setIntHeader("Refresh", 1);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int hp = 0;
        Battle battle = (Battle) req.getSession().getAttribute("battle");
        battle.battle_log.forEach(log -> {
           System.out.println(log);
        });
       if (battle.player1.clientSessions.contains(req.getSession()))
           hp = battle.attack(battle.player1, battle.player2);
       else {
           if (battle.player2.clientSessions.contains(req.getSession()))
            hp = battle.attack(battle.player2, battle.player1);
       }

       boolean needToRedirect = hp == 0;

       if (needToRedirect)
           resp.sendRedirect(req.getContextPath() + "/reward");
       else
            req.getServletContext().getRequestDispatcher("/jsp/fight.jsp").forward(req, resp);
    }
}
