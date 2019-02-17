package cz.HackerGamingCZ.HackerTools.builder;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationBuilder {

    Location location;

    /**
     *
     * @param location
     * @param separator
     */
    public void getLocationFromString(String location, String separator){
        String[] splitted = location.split(separator);

        for(String s : splitted){
            double number;
            try {
                Double.parseDouble(s);
            } catch (NumberFormatException ex){
                HackerTools.getPlugin().getLoggerManager().logException("Couldn't parse location "+location+" with \""+separator+"\" separator", ex);
                return;
            }
        }
    }

    private Location makeLocationFromDoubleArray(String world, Double... coordinates){
        if(world == null || world.isEmpty()){
            throw new NullPointerException("World cannot be null!");
        }
        World bukkitWorld = Bukkit.getWorld(world);
        if(bukkitWorld == null){
            throw new NullPointerException("World \""+ world +"\" cannot be found!");
        }
        if(coordinates.length < 3){
            //TODO better way
            return null;
        }
        if(coordinates.length <= 4)
            return new Location(bukkitWorld, coordinates[0], coordinates[1], coordinates[2]);
        else return new Location(bukkitWorld, coordinates[0], coordinates[1], coordinates[2], Float.parseFloat(String.valueOf(coordinates[3])), Float.parseFloat(String.valueOf(coordinates[4])));
    }

    /**
     *
     * @param location
     * @param separator
     */
    public Location getLocationFromFormatedString(String location, String separator){
        String[] values = location.split(separator);
        return null; //to prevent error
    }

}
