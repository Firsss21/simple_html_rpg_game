package app.matchmaker;


import app.dao.PlayerDao;
import app.models.Player;

import java.util.ArrayList;
import java.util.List;


public class Battle {



    public Battle(int id) {
        this.id = id;
        this.player2 = null;
        this.player1 = null;
    }
    public List<String> battle_log = new ArrayList<>();
    public Player player1;
    public Player player2;
    public int id;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int attack(Player playerWhoAttack, Player playerWhoAttacked) {
        int healthPoints = playerWhoAttacked.getTempHp() - playerWhoAttack.getAttackRate();
        if (healthPoints > 0) {
            playerWhoAttacked.setTempHp(healthPoints);
            battle_log.add(playerWhoAttack.getName()
                    + " ударил по "
                    + playerWhoAttacked.getName()
                    + " (-" + playerWhoAttack.getAttackRate() + ")");
        }
        else {
            if (playerWhoAttack.getTempHp() != 0 && playerWhoAttacked.getTempHp() != 0) {
                playerWhoAttacked.setTempHp(0);
                win(playerWhoAttack, playerWhoAttacked);
                return 0;
            }
        }
        return playerWhoAttacked.getTempHp();
    }

    private void winAward(Player winner) {
        winner.setHealthPoints(winner.getHealthPoints() + 1);
        winner.setAttackRate(winner.getAttackRate() + 1);
        winner.setTempHp(winner.getHealthPoints());
        winner.setElo(winner.getElo() + 1);

        winner.clientSessions.forEach(httpSession -> {
            httpSession.setAttribute("battleResult", "Победа!");
        });
    }

    private void loseAward(Player loser) {
        loser.setHealthPoints(loser.getHealthPoints() + 1);
        loser.setAttackRate(loser.getAttackRate() + 1);
        loser.setTempHp(loser.getHealthPoints());
        loser.setElo(loser.getElo() - 1);

        loser.clientSessions.forEach(httpSession -> {
            httpSession.setAttribute("battleResult", "Поражение..");
        });
    }

    public List<String> getBattle_log() {
        return battle_log;
    }

    public void win(Player playerWon, Player playerLosed) {

        winAward(playerWon);
        loseAward(playerLosed);

        PlayerDao playerDao = new PlayerDao();
        playerDao.update(playerWon);
        playerDao.update(playerLosed);

        FightManager.BATTLES.remove(id);
    }


    public void regToTeam(Player player) {

        if (player1 == null) {
            this.player1 = player;
        } else {
            this.player2 = player;
            start();
        }
    }

    public void start() {
        player2.clientSessions.forEach(httpSession -> {
            httpSession.setAttribute("battle", this);
            httpSession.setAttribute("startTime", System.currentTimeMillis());
        });
        player1.clientSessions.forEach(httpSession -> {
            httpSession.setAttribute("battle", this);
            httpSession.setAttribute("startTime", System.currentTimeMillis());
        });
    }
}
