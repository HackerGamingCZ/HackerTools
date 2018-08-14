package cz.HackerGamingCZ.HackerTools.scoreboard;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.PlaceholderManager;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import cz.HackerGamingCZ.HackerTools.scoreboard.linetype.CustomLineType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HTScoreboard {

    private String header;
    private Scoreboard scoreboard;
    private Objective objective;
    private ArrayList<ScoreboardLine> lines = new ArrayList<>();
    private HTScoreboardUpdater updater;
    private ChatColor primaryColor;
    private ChatColor secondaryColor;
    private HashMap<Team.Option, Team.OptionStatus> options = new HashMap<>();
    private Team playersTeam;
    private HTPlayer player;

    public HTScoreboard setUpdater(HTScoreboardUpdater updater) {
        this.updater = updater;
        return this;
    }

    public HTScoreboard(HTPlayer player, String header, ChatColor primaryColor) {
        this(player, header, primaryColor, ChatColor.GRAY);
    }

    public HTScoreboard(HTPlayer player, String header, ChatColor primaryColor, ChatColor secondaryColor) {
        this.header = header;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("main", "dummy");
        this.primaryColor = primaryColor;
        this.player = player;
        this.secondaryColor = ChatColor.GRAY;
        playersTeam = scoreboard.registerNewTeam("playerTeam");
    }

    public HTScoreboard addTeamOption(Team.Option option, Team.OptionStatus status) {
        options.put(option, status);
        return this;
    }

    public HTScoreboard addLine(ScoreboardLine... line) {
        lines.addAll(Arrays.asList(line));
        return this;
    }

    public HTScoreboard addLine(ArrayList<ScoreboardLine> line) {
        lines.addAll(line);
        return this;
    }


    public void createScoreboard() {
        for (ScoreboardLine scoreboardLine : lines) {
            scoreboard.registerNewTeam(scoreboardLine.getTeam());
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(header);
        PlaceholderManager manager = HackerTools.getPlugin().getPlaceholderManager();
        int i = lines.size();
        for (ScoreboardLine scoreboardLine : lines) {
            Team team = scoreboard.getTeam(scoreboardLine.getTeam());
            if (team == null) {
                continue;
            }
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            team.addPlayer(Bukkit.getServer().getOfflinePlayer(HackerTools.getPlugin().getMechanics().getColors()[i - 1] + "" + primaryColor));
            if (scoreboardLine.getType() instanceof CustomLineType) {
                team.setPrefix(manager.replaceString(scoreboardLine.getTextBefore(), player));
                team.setSuffix(manager.replaceString(scoreboardLine.getTextAfter(), player));
            } else {
                team.setPrefix(secondaryColor + manager.replaceString(scoreboardLine.getTextBefore(), player));
                team.setSuffix(scoreboardLine.getType().getText(player.getPlayer()) + manager.replaceString(scoreboardLine.getTextAfter(), player));
            }
            objective.getScore(HackerTools.getPlugin().getMechanics().getColors()[i - 1] + "" + primaryColor).setScore(i);
            i--;
        }
        playersTeam.addPlayer(player.getPlayer());
        for (Team.Option option : options.keySet()) {
            Team.OptionStatus status = options.get(option);
            if (status == null) {
                continue;
            }
            playersTeam.setOption(option, status);
        }
        player.setBoard(this);
    }

    public void update() {
        if (player == null) {
            return;
        }
        for (ScoreboardLine scoreboardLine : lines) {
            if (scoreboardLine.getType() instanceof CustomLineType) {
                continue;
            }
            Team team = scoreboard.getTeam(scoreboardLine.getTeam());
            if (team == null) {
                continue;
            }
            team.setSuffix(scoreboardLine.getType().getText(player) + scoreboardLine.getTextAfter());
        }
        if (updater == null) {
            return;
        }
        updater.execute(player, this);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public interface HTScoreboardUpdater {

        void execute(HTPlayer player, HTScoreboard htScoreboard);

    }

}
