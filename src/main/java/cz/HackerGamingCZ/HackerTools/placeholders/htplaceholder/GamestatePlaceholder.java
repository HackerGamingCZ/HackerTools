package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class GamestatePlaceholder implements Placeholder {
    @Override
    public String getName() {
        return "GAMESTATE";
    }

    @Override
    public String getReplacement() {
        return HackerTools.getPlugin().getGameState().toString();
    }
}
