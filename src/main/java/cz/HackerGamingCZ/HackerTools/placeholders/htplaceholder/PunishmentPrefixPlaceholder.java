package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class PunishmentPrefixPlaceholder implements Placeholder {


    @Override
    public String getName() {
        return "PUNISHMENTPREFIX";
    }

    @Override
    public String getReplacement() {
        return Lang.PUNISHMENT_PREFIX;
    }
}
