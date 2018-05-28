package cz.HackerGamingCZ.HackerTools.scoreboard;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ScoreboardLine {

    private LineType type;
    private String textBefore;
    private String textAfter;
    private String team;

    public ScoreboardLine(String team, LineType type, String textBefore){
        this.type = type;
        this.textBefore = textBefore;
        this.team = team;
    }

    public ScoreboardLine(String team, String textBefore, String textAfter){
        this.type = LineType.CUSTOM;
        this.textBefore = textBefore;
        this.textAfter = textAfter;
        this.team = team;
    }

    public ScoreboardLine(){
        this.type = LineType.EMPTY;
        textAfter = "";
        textBefore = "";
        team = String.valueOf(HackerTools.getPlugin().getRandomManager().nextInt(100000));
    }

    public LineType getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

    public String getTextAfter() {
        return textAfter;
    }

    public String getTextBefore() {
        return textBefore;
    }


    public enum LineType{
        ONLINEPLAYERS(Placeholders.ONLINEPLAYERS),
        GAMESTATE(Placeholders.GAMESTATE),
        COUNTDOWN(Placeholders.COUNTDOWN),
        ECONOMY(),
        TEAM(),
        KIT(),
        EMPTY(),
        PLAYERNAME(Placeholders.PLAYERNAME),
        CUSTOM();

        private Placeholders placeholder;

        LineType() {
        }

        LineType(Placeholders placeholder){
            this.placeholder = placeholder;
        }

        public String getText(Player player){
            return placeholder.getPlaceholder().getReplacement(player);
        }

        public String getText(OfflinePlayer player){
            return placeholder.getPlaceholder().getReplacement(player);
        }

        public String getText(){
            return placeholder.getPlaceholder().getReplacement();
        }

        public String getText(HTPlayer htPlayer) {
            return placeholder.getPlaceholder().getReplacement(htPlayer);
        }
    }

}
