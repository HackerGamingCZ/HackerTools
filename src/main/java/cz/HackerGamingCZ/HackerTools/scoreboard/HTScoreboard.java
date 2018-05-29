package cz.HackerGamingCZ.HackerTools.scoreboard;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.scoreboard.linetype.CustomLineType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class HTScoreboard implements Registrable {

    private String header;
    private Scoreboard scoreboard;
    private Objective objective;
    private ArrayList<ScoreboardLine> lines = new ArrayList<>();
    private Runnable updater;
    private ChatColor primaryColor;
    private ChatColor secondaryColor;

    public void setUpdater(Runnable updater) {
        this.updater = updater;
    }

    public HTScoreboard(String name, String header, ChatColor primaryColor) {
        this.header = header;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(name, "dummy");
        this.primaryColor = primaryColor;
        this.secondaryColor = ChatColor.GRAY;
    }

    public HTScoreboard(String name, String header, ChatColor primaryColor, ChatColor secondaryColor) {
        this.header = header;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(name, "dummy");
        this.secondaryColor = secondaryColor;
        this.primaryColor = primaryColor;
    }

    public void addLine(ScoreboardLine line) {
        lines.add(line);
    }

    public void createScoreboard(HTPlayer htPlayer) {
        int i = 16;
        for (ScoreboardLine scoreboardLine : lines) {
            Team team = scoreboard.getTeam(scoreboardLine.getTeam());
            if (team == null) {
                continue;
            }
            team.addPlayer(Bukkit.getServer().getOfflinePlayer(HackerTools.getPlugin().getMechanics().getColors()[i - 1] + "" + primaryColor));
            if (scoreboardLine.getType() instanceof CustomLineType) {
                team.setPrefix(secondaryColor + scoreboardLine.getTextBefore());
                team.setSuffix(scoreboardLine.getTextAfter());
            } else {
                team.setPrefix(secondaryColor + scoreboardLine.getTextBefore());
                team.setSuffix(scoreboardLine.getType().getText(htPlayer.getPlayer()));
            }
            objective.getScore(HackerTools.getPlugin().getMechanics().getColors()[i - 1] + "" + primaryColor).setScore(i);
            i--;
        }
        htPlayer.setBoard(this);
    }

    public void update(Player player) {
        for (ScoreboardLine scoreboardLine : lines) {
            if (scoreboardLine.getType() instanceof CustomLineType) {
                continue;
            }
            Team team = scoreboard.getTeam(scoreboardLine.getTeam());
            if (team == null) {
                continue;
            }
            team.setSuffix(scoreboardLine.getType().getText(player));
        }
        if (updater == null) {
            return;
        }
        updater.run();
    }

    @Override
    public void register() {
        for (ScoreboardLine scoreboardLine : lines) {
            scoreboard.registerNewTeam(scoreboardLine.getTeam());
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(header);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
