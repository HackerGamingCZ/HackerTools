package cz.HackerGamingCZ.HackerTools;

import org.bukkit.command.CommandSender;

public interface CommandArgument {

    void execute(CommandSender sender, String... arguments);

}

