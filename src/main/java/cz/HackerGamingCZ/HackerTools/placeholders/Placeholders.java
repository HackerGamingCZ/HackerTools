package cz.HackerGamingCZ.HackerTools.placeholders;

import cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder.*;

public enum Placeholders {

    ONLINEPLAYERS(new OnlinePlayerCountPlaceholder()),
    PLAYERNAME(new PlayerNamePlaceholder()),
    COUNTDOWN(new CountdownPlaceholder()),
    DEBUGPREFIX(new DebugPrefixPlaceholder()),
    ERRORPREFIX(new ErrorPrefixPlaceholder()),
    HTPREFIX(new HTPrefixPlaceholder()),
    MAXPLAYERS(new MaxPlayersPlaceholder()),
    MINPLAYERS(new MinPlayersPlaceholder()),
    PLUGINAUTHOR(new PluginAuthorPlaceholder()),
    PLUGINNAME(new PluginNamePlaceholder()),
    GAMESTATE(new GamestatePlaceholder());

    private Placeholder placeholder;

    Placeholders(Placeholder placeholder){
        this.placeholder = placeholder;
    }

    public Placeholder getPlaceholder() {
        return placeholder;
    }

}
