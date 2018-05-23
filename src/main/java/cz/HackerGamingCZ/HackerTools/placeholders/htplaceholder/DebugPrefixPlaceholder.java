package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class DebugPrefixPlaceholder implements Placeholder {
    @Override
    public String getName() {
        return "DEBUGPREFIX";
    }

    @Override
    public String getReplacement() {
        return Lang.DEBUG_PREFIX;
    }
}
