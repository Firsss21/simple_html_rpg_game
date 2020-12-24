package app.servlets;

import app.models.Player;
import app.models.User;
import app.dao.PlayerDao;
import app.dao.UsersDao;
import app.utils.Md5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        // Одно из полей пустое
            // Показать ошибку
        // Логин верный - пароль неверный
            // Показать ошибку

            // Логин верный - пароль верный
                // загрузить юзера из бд
            // Такого логина нет - регистрация нового юзера

            // редирект на хоум


        UsersDao usersDao = new UsersDao();
        PlayerDao playerDao = new PlayerDao();

        if (usersDao.existByStringValueColumn(name, "login"))
        {
            int userIdToSelect = usersDao.get(name, "login").getId();
         //   System.out.println(userIdToSelect);
           // System.out.println("pass eq = " + (usersDao.get(userIdToSelect).getPassword().equals(Md5.crypt(password))));
//            usersDao.get(userIdToSelect).checkPassword()
//            Player player = playerDao.get(userIdToSelect);
//            System.out.println(player.getHealthPoints());
            System.out.println("login");
        } else {
            System.out.println("krivo zaregano");
            User user = new User();
            user.setName(name);
            user.setPassword(Md5.crypt(password));

            usersDao.save(user);

            Player player = new Player(name);
            playerDao.save(player);

        }
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

        HttpSession session = req.getSession();
        session.setAttribute("user", name);
        // перенаправляем на страницу home
        resp.sendRedirect(req.getContextPath() + "/home");
//        resp.sendRedirect(req.);

//        req.getServletContext().getRequestDispatcher("/menu").forward(req, resp);
        // Если такой юзер имеется в бд - логин, иначе новый юзер.

//        resp.sendRedirect(req.getContextPath() + "/login");
    }
}


