package cz.HackerGamingCZ.HackerTools.sign;

import cz.HackerGamingCZ.HackerTools.PlayerAction;
import org.bukkit.Location;

public class Sign {

    private String[] lines = new String[4];
    private PlayerAction action;

    /**
     * @param action What happens, when the player clicks on the sign
     * @param lines Lines of the sign. If empty, leave empty string.
     */
    public void Sign(PlayerAction action, String... lines){
        //Gets the number of lines. If more than 4, set 4.
        int x = lines.length < 4 ? lines.length : 4;
        //Loop all values in lines parameter and set all X of them to the class lines variable.
        for(int i = 0; i < x-1; i++){
            this.lines[i] = lines[i];
        }
        this.action = action;
    }

    public void place(Location location){

    }


    //Getters
    public PlayerAction getAction() {
        return action;
    }

    public String[] getLines() {
        return lines;
    }
}
