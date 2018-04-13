package cz.HackerGamingCZ.HackerTools.enums;

import cz.HackerGamingCZ.HackerTools.Lang;
import cz.HackerGamingCZ.HackerTools.Main;
import org.bukkit.Bukkit;

public enum Placeholder {

    PLUGINAUTHOR(Main.getPlugin().getPdf().getAuthors().get(0)),
    PLUGINNAME(Main.getPlugin().getPdf().getName()),
    PLUGINVERSION(Main.getPlugin().getPdf().getVersion()),
    ONLINEPLAYERS(String.valueOf(Bukkit.getOnlinePlayers().size())),
    PLAYERNAME(),
    DEBUGPREFIX(Lang.DEBUG_PREFIX);

    private String placeholder;
    private String replacement;

    Placeholder(){
        this.placeholder = toString();
        this.replacement = null;
    }

    Placeholder(String replacement){
        this.placeholder = toString();
        this.replacement = replacement;
    }

    public String getReplacement() {
        return replacement;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    @Override
    public String toString() {
        return "[_"+name()+"_]";
    }
}