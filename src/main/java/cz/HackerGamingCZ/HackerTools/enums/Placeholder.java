package cz.HackerGamingCZ.HackerTools.enums;
import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.Lang;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum Placeholder {


    PLUGINAUTHOR(HackerTools.getPlugin().getPdf().getAuthors().get(0)),
    PLUGINNAME(HackerTools.getPlugin().getPdf().getName()),
    PLUGINVERSION(HackerTools.getPlugin().getPdf().getVersion()),
    ONLINEPLAYERS(String.valueOf(Bukkit.getOnlinePlayers().size()), "getOnlinePlayerCount"),
    MINPLAYERS(String.valueOf(HackerTools.getPlugin().getMinigameAPI().getMinPlayers()), "getMinPlayers"),
    MAXPLAYERS(String.valueOf(HackerTools.getPlugin().getMinigameAPI().getMaxPlayers()), "getMaxPlayers"),
    PLAYERNAME(),
    DEBUGPREFIX(Lang.DEBUG_PREFIX),
    HTPREFIX(Lang.HACKERTOOLS_PREFIX),
    COUNTDOWN();

    private String placeholder;
    private String replacement;
    private Class aClass = Placeholder.class;
    private String methodName;

    Placeholder(){
        this.placeholder = toString();
        this.replacement = null;
    }

    Placeholder(String replacement) {
        this.placeholder = toString();
        this.replacement = replacement;
    }

    Placeholder(String replacement, String methodName) {
        this.placeholder = toString();
        this.replacement = replacement;
        this.methodName = methodName;
    }

    public void update(){
        if(methodName == null){
            return;
        }
        try{
            Method method = aClass.getMethod(methodName);
            setReplacement(String.valueOf(method.invoke(Bukkit.class)));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            e.printStackTrace();
        }
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


    public static int getOnlinePlayerCount(){
        return Bukkit.getOnlinePlayers().size();
    }

    public static int getMinPlayers(){
        return HackerTools.getPlugin().getMinigameAPI().getMinPlayers();
    }

    public static int getMaxPlayers(){
        return HackerTools.getPlugin().getMinigameAPI().getMaxPlayers();
    }
}