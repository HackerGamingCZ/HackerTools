package cz.HackerGamingCZ.HackerTools.teams;


public class TeamManager {

    private boolean teamDamage = false;
    private boolean teamChat = true;

    public void disableTeamDamage(){
        teamDamage = false;
    }

    public void enableTeamDamage(){
        teamDamage = true;
    }


    public void disableTeamChat(){
        teamChat = false;
    }

    public void enableTeamChat(){
        teamChat = true;
    }

    public boolean isTeamDamage() {
        return teamDamage;
    }

    public boolean isTeamChat() {
        return teamChat;
    }
}
