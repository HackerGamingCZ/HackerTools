package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class PluginAuthorPlaceholder implements Placeholder {
    @Override
    public String getName() {
        return "PLUGINAUTHOR";
    }

    @Override
    public String getReplacement() {
        return HackerTools.getPlugin().getPdf().getAuthors().get(0);
    }
}
