package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class ErrorPrefixPlaceholder implements Placeholder {


    @Override
    public String getName() {
        return "ERRORPREFIX";
    }

    @Override
    public String getReplacement() {
        return Lang.ERROR_PREFIX;
    }
}
