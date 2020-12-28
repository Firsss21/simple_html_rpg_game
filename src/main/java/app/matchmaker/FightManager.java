package app.matchmaker;

import app.models.Player;
import app.utils.CustomExecutor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FightManager {

    public static final Map<Integer, Battle> BATTLES = Collections.synchronizedMap(new ConcurrentHashMap<>()); // Fights
    protected static int nextBattleId = 0;

    public static synchronized Battle registerPlayer(Player player)
    {

        for(Battle current: BATTLES.values())
        {
              if (current.player2 != null) {
                break;
            }

            if (current.player1 == player) return null;

            current.regToTeam(player);
             return current;
        }

        Battle battle = new Battle(FightManager.nextBattleId++);
        battle.regToTeam(player);
        BATTLES.put(battle.id, battle);

        return battle;
    }

}
