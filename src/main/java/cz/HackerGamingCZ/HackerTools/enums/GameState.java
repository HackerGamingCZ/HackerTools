package cz.HackerGamingCZ.HackerTools.enums;

import cz.HackerGamingCZ.HackerTools.Lang;

public enum GameState {

    SETUP("§cSetup mode", Lang.DEFAULT_SETUP_GLOBAL_MESSAGE, Lang.DEFAULT_SETUP_PLAYER_MESSAGE, JoinType.ADMIN),
    WAITING("§cGame is waiting for players...", Lang.DEFAULT_WAITING_GLOBAL_MESSAGE, Lang.DEFAULT_WAITING_PLAYER_MESSAGE, JoinType.PLAYER),
    STARTING("§cGame is starting...", Lang.DEFAULT_STARTING_GLOBAL_MESSAGE, Lang.DEFAULT_STARTING_PLAYER_MESSAGE, JoinType.PLAYER),
    INGAME("§cGame is in progress", Lang.DEFAULT_INGAME_GLOBAL_MESSAGE, Lang.DEFAULT_INGAME_PLAYER_MESSAGE, JoinType.SPECTATOR),
    END("§cGame is over", Lang.DEFAULT_END_GLOBAL_MESSAGE, Lang.DEFAULT_END_PLAYER_MESSAGE, JoinType.NOBODY),
    RESET("§cGame is resetting...", Lang.DEFAULT_RESET_GLOBAL_MESSAGE, Lang.DEFAULT_RESET_PLAYER_MESSAGE, JoinType.NOBODY);

    private String motd;
    private JoinType joinType;
    private String globalMessage;
    private String messageToPlayer;

    GameState(String motd, String globalMessage, String messageToPlayer, JoinType joinType){
        this.motd = motd;
        this.joinType = joinType;
        this.globalMessage = globalMessage;
        this.messageToPlayer = messageToPlayer;
    }

    //SET & GET
    public void setMotd(String motd) {
        this.motd = motd;
    }
    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }
    public void setMessageToPlayer(String messageToPlayer) { this.messageToPlayer = messageToPlayer; }
    public void setGlobalMessage(String globalMessage) { this.globalMessage = globalMessage; }

    public String getMessageToPlayer() { return messageToPlayer; }
    public String getMotd() { return motd; }
    public String getGlobalMessage() { return globalMessage; }
    public JoinType getJoinType() {
        return joinType;
    }

    public enum JoinType {

        PLAYER(),
        SPECTATOR(),
        NOBODY(),
        ADMIN("ht.adminjoin");

        private String permission;

        JoinType(){
        }

        JoinType(String permission){
            this.permission = permission;
        }

        //SET & GET
        public String getPermission() {
            return permission;
        }
        public void setPermission(String permission) {
            this.permission = permission;
        }
    }

}

