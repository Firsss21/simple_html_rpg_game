package app;

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



    public Player(String name) {
        this.name = name;
        this.elo = 100;
        this.healthPoints = 100;
        this.attackRate = 10;
        this.inBattle = false;
    }

    public Player(int id) {
        // get data from database
    }


}
