package cz.HackerGamingCZ.HackerTools.scoreboard.linetype;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;

public class PlayersOutOfMaximumLineType implements LineType {

    @Override
    public String getText() {
        return Placeholders.ONLINEPLAYERS.getPlaceholder().getReplacement() + "/" + Placeholders.MAXPLAYERS.getPlaceholder().getReplacement();
    }

}
