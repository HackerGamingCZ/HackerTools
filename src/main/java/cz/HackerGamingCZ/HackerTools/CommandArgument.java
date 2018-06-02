package cz.HackerGamingCZ.HackerTools;

import cz.HackerGamingCZ.HackerTools.players.HTPlayer;
import org.bukkit.command.CommandSender;

public interface CommandArgument {

    void execute(CommandSender sender, String... arguments);

}

