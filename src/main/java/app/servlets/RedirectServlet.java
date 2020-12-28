package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/123")
public class RedirectServlet extends HttpServlet {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/css/styles.css")));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req
                .getRequestURI()
                .substring(req.getContextPath().length())
                .replaceAll("[/]+$", "");

        if (!ALLOWED_PATHS.contains(path))
            resp.sendRedirect(req.getContextPath() + "/login");

    }
}
