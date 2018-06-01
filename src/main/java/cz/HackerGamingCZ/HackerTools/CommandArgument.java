package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;

public interface CommandArgument {

    void execute(HTPlayer player, Object... arguments);

}

