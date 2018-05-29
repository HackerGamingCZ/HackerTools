package cz.HackerGamingCZ.HackerTools.scoreboard.linetype;

import cz.HackerGamingCZ.HackerTools.placeholders.Placeholders;

public class GameStateLineType implements LineType {

    @Override
    public String getText() {
        return Placeholders.GAMESTATE.getPlaceholder().getReplacement();
    }

}
