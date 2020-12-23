package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // ссылка на хранилище пользователей
//    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
//        this.usersRepository = new UsersRepositoryInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);

        // если есть чел в системе - логин
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // вытаскиваем из запроса имя пользователя и его пароль
        String name = req.getParameter("name");
        String password = req.getParameter("password");


        System.out.println(name);
        System.out.println(password);
        // если пользователь есть в системе
//        if (usersRepository.isExist(name, password)) {
//            // создаем для него сессию
//            HttpSession session = req.getSession();
//            // кладем в атрибуты сессии атрибут user с именем пользователя
//            session.setAttribute("user", name);
//            // перенаправляем на страницу home
//            req.getServletContext().getRequestDispatcher("/home").forward(req, resp);
//        } else {
//            resp.sendRedirect(req.getContextPath() + "/login");
//        }


        // Если такой юзер имеется в бд - логин, иначе новый юзер.

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}


