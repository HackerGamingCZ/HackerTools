package cz.HackerGamingCZ.HackerTools.testing;

import cz.HackerGamingCZ.HackerTools.scoreboard.Scoreboard;
import cz.HackerGamingCZ.HackerTools.scoreboard.ScoreboardLine;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class HTScoreboard implements Scoreboard{

    @Override
    public ChatColor getMainColor() {
        return ChatColor.GREEN;
    }

    @Override
    public ArrayList<ScoreboardLine> getLines() {
        ArrayList<ScoreboardLine> list = new ArrayList<>();
        list.add(new ScoreboardLine("a", ScoreboardLine.LineType.GAMESTATE, "State: "));
        list.add(new ScoreboardLine("b", ScoreboardLine.LineType.PLAYERNAME, "Nick: "));
        list.add(new ScoreboardLine("c", ScoreboardLine.LineType.ONLINEPLAYERS, "Hráčů: "));
        list.add(new ScoreboardLine("d", "Měnící int: ", new Random().nextInt(200)+""));
        return list;
    }

    @Override
    public String getHeader() {
        return "§cHackerTools";
    }

    @Override
    public void updateCustoms(Player player) {
        getTeam("d").setSuffix(new Random().nextInt(200)+"");
    }
}
