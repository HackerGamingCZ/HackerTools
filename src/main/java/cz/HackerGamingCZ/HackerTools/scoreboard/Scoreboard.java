package cz.HackerGamingCZ.HackerTools.scoreboard;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public interface Scoreboard {

    org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    Objective obj = board.registerNewObjective("main", "dummy");
    ChatColor[] colors = ChatColor.values();

    ChatColor getMainColor();
    ArrayList<ScoreboardLine> getLines();
    default void load(){
        for(ScoreboardLine scoreboardLine : getLines()){
            board.registerNewTeam(scoreboardLine.getTeam());
        }
    }

    String getHeader();

    default void createScoreboard(HTPlayer player){
        int i = 16;
        for(ScoreboardLine scoreboardLine : getLines()){
            Team team = board.getTeam(scoreboardLine.getTeam());
            if(team == null){
                continue;
            }
            team.addPlayer(Bukkit.getServer().getOfflinePlayer(colors[i-1]+""+getMainColor()));
            if(scoreboardLine.getType() == ScoreboardLine.LineType.CUSTOM) {
                team.setPrefix("§7"+scoreboardLine.getTextBefore());
                team.setSuffix(scoreboardLine.getTextAfter());
            } else{
                team.setPrefix("§7"+scoreboardLine.getTextBefore());
                team.setSuffix(scoreboardLine.getType().getText(player.getPlayer()));
            }
            obj.getScore(colors[i-1]+""+getMainColor()).setScore(i);
            i--;
        }
        player.setBoard(this);
    }

    default org.bukkit.scoreboard.Scoreboard getBoard(){
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(getHeader());
        return board;
    }

    default void update(Player player){
        for(ScoreboardLine scoreboardLine : getLines()) {
            if(scoreboardLine.getType() == ScoreboardLine.LineType.CUSTOM){
                continue;
            }
            Team team = board.getTeam(scoreboardLine.getTeam());
            if(team == null){
                continue;
            }
            team.setSuffix(scoreboardLine.getType().getText(player));
        }
    }

    default Team getTeam(String str){
        return board.getTeam(str);
    }

    void updateCustoms(Player player);

}
