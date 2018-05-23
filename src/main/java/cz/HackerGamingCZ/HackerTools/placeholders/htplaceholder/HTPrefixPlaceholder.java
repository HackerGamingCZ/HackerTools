package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class HTPrefixPlaceholder implements Placeholder {
    @Override
    public String getName() {
        return "HTPREFIX";
    }

    @Override
    public String getReplacement() {
        return Lang.HACKERTOOLS_PREFIX;
    }
}
