package cz.HackerGamingCZ.HackerTools.scoreboard.linetype;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;

public class CountDownLineType implements LineType {

    @Override
    public String getText() {
        return Placeholders.COUNTDOWN.getPlaceholder().getReplacement();
    }

}
