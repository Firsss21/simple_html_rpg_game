package app.models;

public class Player {

    // tech

    private int id;
    public boolean inBattle;

    // params

    private String name;
    private int healthPoints;
    private int attackRate;

    // statistic

    private int elo;

    // constructors

//    public Player(String name) {
//        this.name = name;
//        this.elo = 100;
//        this.healthPoints = 100;
//        this.attackRate = 10;
//        this.inBattle = false;
//    }

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;
        this.elo = 100;
        this.healthPoints = 100;
        this.attackRate = 10;
        this.inBattle = false;
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