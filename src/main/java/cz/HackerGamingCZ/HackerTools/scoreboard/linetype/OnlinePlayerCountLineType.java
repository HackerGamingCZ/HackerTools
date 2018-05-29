package cz.HackerGamingCZ.HackerTools.scoreboard.linetype;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;

public class OnlinePlayerCountLineType implements LineType {

    @Override
    public String getText() {
        return Placeholders.ONLINEPLAYERS.getPlaceholder().getReplacement();
    }

}
