package app.servlets.filters;


import app.Main;
import app.matchmaker.Battle;
import app.matchmaker.FightManager;
import app.models.Player;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("", "/login", "/css/styles.css")));


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);


        String path = request
                .getRequestURI()
                .substring(request.getContextPath().length())
                .replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        if (allowedPath || session != null && session.getAttribute("player") != null)
        {

            if (session != null
                    && session.getAttribute("battle") != null
                    && !path.equals("/fight")
                    && (!path.equals("/reward")
                    && session.getAttribute("battleResult") == null))
            {

                long time = System.currentTimeMillis() - (long) session.getAttribute("startTime");

                if (time > Main.WAITING_TIME_BEFORE_FIGHT)
                {
                     response.sendRedirect(request.getContextPath() + "/fight");
                }
                else
                {
                    session.setAttribute("timeToStart", (int) time/1000);
                    request.getServletContext().getRequestDispatcher("/jsp/waiting.jsp").forward(request, response);
                }

            }
            else
            {
                 chain.doFilter(request, response);
            }
       }
        else
       {
           response.sendRedirect(request.getContextPath() + "/login");
       }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

