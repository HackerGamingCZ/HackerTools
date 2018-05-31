package cz.HackerGamingCZ.HackerTools.scoreboard;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.scoreboard.linetype.*;

public class ScoreboardLine {

    private LineType type;
    private String textBefore;
    private String textAfter = "";
    private String team;

    @Deprecated
    public ScoreboardLine(String team, LineType type, String textBefore){
        this.type = type;
        this.textBefore = textBefore;
        this.team = team;
    }

    public ScoreboardLine(String team, String textBefore, LineType type) {
        this.type = type;
        this.textBefore = textBefore;
        this.team = team;
    }


    public ScoreboardLine(String team, String textBefore, LineType type, String textAfter) {
        this.type = type;
        this.textBefore = textBefore;
        this.team = team;
        this.textAfter = textAfter;
    }

    public ScoreboardLine(String team, String textBefore, String textAfter){
        this.type = new CustomLineType();
        this.textBefore = textBefore;
        this.textAfter = textAfter;
        this.team = team;
    }

    public ScoreboardLine(){
        this.type = new EmptyLineType();
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


}
