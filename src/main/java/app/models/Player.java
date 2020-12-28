package app.models;

import app.matchmaker.Battle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class Player {

    // tech

    private int id;
    private boolean inBattle;
    public Battle battle = null;
    public final Set<HttpSession> clientSessions = Collections.synchronizedSet(new HashSet<>());

    // params

    private String name;
    private int healthPoints;
    private int attackRate;
    private int tempHp;
    // statistic

    private int elo;

    // constructors

    public String getName() {
        return name;
    }

    public int getTempHp() {
        return tempHp;
    }

    public void setTempHp(int tempHp) {
        this.tempHp = tempHp;
    }

    public Player(String name) {
        this.name = name;
        this.elo = 100;
        this.healthPoints = 100;
        this.attackRate = 10;
        this.inBattle = false;
        this.tempHp = this.healthPoints;
        // get data from database
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player() {

    }


    // getters/setters

    public int getId() {
        return id;
    }

    public boolean isInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackRate() {
        return attackRate;
    }

    public void setAttackRate(int attackRate) {
        this.attackRate = attackRate;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }




}
