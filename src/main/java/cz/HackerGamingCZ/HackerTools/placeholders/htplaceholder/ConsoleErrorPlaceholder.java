package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class ConsoleErrorPlaceholder implements Placeholder {


    @Override
    public String getName() {
        return "CONSOLEERROR";
    }

    @Override
    public String getReplacement() {
        return Lang.CONSOLE_ERROR;
    }
}
