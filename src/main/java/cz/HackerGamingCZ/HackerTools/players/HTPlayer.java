package cz.HackerGamingCZ.HackerTools.players;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.enums.GameState;
import cz.HackerGamingCZ.HackerTools.events.PlayerJoinTeamEvent;
import cz.HackerGamingCZ.HackerTools.scoreboard.Scoreboard;
import cz.HackerGamingCZ.HackerTools.teams.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HTPlayer {

    private Player player;
    private HTPlayer lastHittedBy;
    private Team team;
    private Team previousTeam;
    private Scoreboard board;

    public HTPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
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

    public Scoreboard getBoard() {
        return board;
    }

    public void setBoard(Scoreboard board) {
        this.board = board;
        player.setScoreboard(board.getBoard());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void updateScoreboard(){
        if(board == null){
            return;
        }
        board.update(player);
        board.updateCustoms(player);
    }
}
