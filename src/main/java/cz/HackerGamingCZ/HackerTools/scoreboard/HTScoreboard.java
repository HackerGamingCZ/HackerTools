package cz.HackerGamingCZ.HackerTools.scoreboard;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.PlayerAction;
import cz.HackerGamingCZ.HackerTools.Registrable;
import cz.HackerGamingCZ.HackerTools.placeholders.PlaceholderManager;
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
import java.util.HashMap;

public class HTScoreboard implements Registrable {

    private String header;
    private Scoreboard scoreboard;
    private Objective objective;
    private ArrayList<ScoreboardLine> lines = new ArrayList<>();
    private PlayerAction updater;
    private ChatColor primaryColor;
    private ChatColor secondaryColor;
    private HashMap<Team.Option, Team.OptionStatus> options = new HashMap<>();
    private Team playersTeam;

    public void setUpdater(PlayerAction updater) {
        this.updater = updater;
    }

    public HTScoreboard(String name, String header, ChatColor primaryColor) {
        this.header = header;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(name, "dummy");
        this.primaryColor = primaryColor;
        this.secondaryColor = ChatColor.GRAY;
        playersTeam = scoreboard.registerNewTeam("playerTeam");
    }

    public HTScoreboard(String name, String header, ChatColor primaryColor, ChatColor secondaryColor) {
        this.header = header;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(name, "dummy");
        this.secondaryColor = secondaryColor;
        this.primaryColor = primaryColor;
        playersTeam = scoreboard.registerNewTeam("playerTeam");
    }

    public void addTeamOption(Team.Option option, Team.OptionStatus status) {
        options.put(option, status);
    }

    public void addLine(ScoreboardLine line) {
        lines.add(line);
    }

    public void addLine(ScoreboardLine... line) {
        for (ScoreboardLine scoreboardLine : line) {
            lines.add(scoreboardLine);
        }
    }

    public void createScoreboard(HTPlayer htPlayer) {
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
                team.setPrefix(manager.replaceString(scoreboardLine.getTextBefore(), htPlayer));
                team.setSuffix(manager.replaceString(scoreboardLine.getTextAfter(), htPlayer));
            } else {
                team.setPrefix(secondaryColor + manager.replaceString(scoreboardLine.getTextBefore(), htPlayer));
                team.setSuffix(scoreboardLine.getType().getText(htPlayer.getPlayer()) + manager.replaceString(scoreboardLine.getTextAfter(), htPlayer));
            }
            objective.getScore(HackerTools.getPlugin().getMechanics().getColors()[i - 1] + "" + primaryColor).setScore(i);
            i--;
        }
        playersTeam.addPlayer(htPlayer.getPlayer());
        for (Team.Option option : options.keySet()) {
            Team.OptionStatus status = options.get(option);
            if (status == null) {
                continue;
            }
            playersTeam.setOption(option, status);
        }
        htPlayer.setBoard(this);
    }

    public void update(HTPlayer player) {
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
        updater.execute(player);
    }

    public void update(Player player) {
        HTPlayer htPlayer = HackerTools.getPlugin().getPlayerManager().getPlayer(player);
        update(htPlayer);
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
