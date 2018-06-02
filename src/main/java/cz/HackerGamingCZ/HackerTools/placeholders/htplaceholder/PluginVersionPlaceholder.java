package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class PluginVersionPlaceholder implements Placeholder {


    @Override
    public String getName() {
        return "PLUGINVERSION";
    }

    @Override
    public String getReplacement() {
        return HackerTools.getPlugin().getPdf().getVersion();
    }
}
