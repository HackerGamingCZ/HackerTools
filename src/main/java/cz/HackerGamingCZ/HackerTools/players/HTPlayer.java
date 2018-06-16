package cz.HackerGamingCZ.HackerTools.players;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.config.SimpleConfig;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.events.PlayerJoinTeamEvent;
import cz.HackerGamingCZ.HackerTools.gui.GUI;
import cz.HackerGamingCZ.HackerTools.scoreboard.HTScoreboard;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HTPlayer {

    private Player player;
    private HTPlayer lastHittedBy;
    private Team team;
    private Team previousTeam;
    private HTScoreboard board;
    private boolean debug;
    private SimpleConfig configFile;

    public HTPlayer(Player player) {
        this.player = player;
        configFile = HackerTools.getPlugin().getSimpleConfigManager().getNewConfig("players/" + player.getName() + ".yml");
        configFile.check("UUID", player.getUniqueId().toString());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("y/M/d HH:mm");
        configFile.check("first-connection", sdf.format(cal.getTime()));
        configFile.set("last-connection", sdf.format(cal.getTime()));
        configFile.check("debug", true);
        this.debug = configFile.getBoolean("debug");
    }

    public Player getPlayer() {
        return player;
    }

    private void saveToConfig() {
        configFile.set("debug", debug);
        configFile.saveConfig();
    }

    public Team getTeam() {
        return team;
    }

    public HTPlayer getLastHittedBy() {
        return lastHittedBy;
    }

    public void setLastHittedBy(HTPlayer lastHittedBy) {
        this.lastHittedBy = lastHittedBy;
    }

    public boolean isSpectator() {
        return team == HackerTools.getPlugin().getSpectatorTeam();
    }

    public void setTeam(Team team) {
        previousTeam = this.team;
        PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(team, this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        this.team = team;
    }

    public Team getPreviousTeam() {
        return previousTeam;
    }

    public void reconnect() {
        team = previousTeam;
        if (GameState.JoinType.RECONNECT.getGlobalMessage() != null) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                HackerTools.getPlugin().getChatManager().sendPlayerMessage(p, GameState.JoinType.RECONNECT.getGlobalMessage(), player.getName());
            }
        }
        if (GameState.JoinType.RECONNECT.getMessageToPlayer() != null) {
            HackerTools.getPlugin().getChatManager().sendPlayerMessage(player, GameState.JoinType.RECONNECT.getMessageToPlayer());
        }
    }

    public HTScoreboard getBoard() {
        return board;
    }

    public void setBoard(HTScoreboard board) {
        player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(board.getScoreboard());
        this.board = board;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateScoreboard(){
        if(board == null){
            return;
        }
        board.update();
    }

    public boolean hasTeam() {
        return team != null && !isSpectator();
    }

    public void openGUI(GUI gui) {
        gui.open(this, 0);
    }

    public void joinTeam(Team team) {
        team.join(this);
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
        saveToConfig();
    }

    public boolean isDebug() {
        return debug;
    }

    public SimpleConfig getConfigFile() {
        return configFile;
    }
}
