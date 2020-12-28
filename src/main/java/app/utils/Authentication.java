package app.utils;

import app.Main;
import app.dao.PlayerDao;
import app.dao.UsersDao;
import app.models.Player;
import app.models.User;


public class Authentication {

    private static UsersDao usersDao = new UsersDao();
    private static PlayerDao playerDao = new PlayerDao();

    public static boolean userExist(String name) {
       return usersDao.existByStringValueColumn(name, "login");
    }

    public static Player loginAndGetPlayer(String name, String password) {

        Player player;
        User user = usersDao.get(name, "login");

        String passwordFromBd = user.getPassword();
        String inputPassword = Md5.crypt(password);

        if (inputPassword.equals(passwordFromBd)) {
            if (!Main.PLAYERS.containsKey(name)) {
                player = playerDao.get(user.getId());
                Main.PLAYERS.put(name, player);
            } else {
                player = Main.PLAYERS.get(name);
            }
            return player;
        }
        return null;
    }



    public static Player createUserAndGetPlayer(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(Md5.crypt(password));

        usersDao.save(user);

        Player player = new Player(name);
        playerDao.save(player);

        Main.PLAYERS.put(name, player);

        return player;
    }

}
