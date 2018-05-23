package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class MinPlayersPlaceholder implements Placeholder {
    @Override
    public String getName() {
        return "MINPLAYERS";
    }

    @Override
    public String getReplacement() {
        if(HackerTools.getPlugin().getMinigameManager() == null){
            return null;
        }
        return String.valueOf(HackerTools.getPlugin().getMinigameManager().getMinPlayers());
    }
}
