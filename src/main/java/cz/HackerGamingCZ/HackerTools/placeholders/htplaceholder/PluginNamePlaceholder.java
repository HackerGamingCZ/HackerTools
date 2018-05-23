package cz.HackerGamingCZ.HackerTools.placeholders.htplaceholder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.placeholders.Placeholder;

public class PluginNamePlaceholder implements Placeholder {
    @Override
    public String getName() {
        return "PLUGINNAME";
    }

    @Override
    public String getReplacement() {
        return HackerTools.getPlugin().getPdf().getName();
    }
}
