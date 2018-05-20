package cz.HackerGamingCZ.HackerTools.enums;

import cz.HackerGamingCZ.HackerTools.Lang;

public enum GameState {

    SETUP("§cSetup mode", JoinType.ADMIN),
    WAITING("§cGame is waiting for players...", JoinType.PLAYER),
    STARTING("§cGame is starting...", JoinType.PLAYER),
    INGAME("§cGame is in progress", JoinType.SPECTATOR),
    END("§cGame is over", JoinType.NOBODY),
    RESET("§cGame is resetting...", JoinType.NOBODY);

    private String motd;
    private JoinType joinType;

    GameState(String motd, JoinType joinType){
        this.motd = motd;
        this.joinType = joinType;
    }

    //SET & GET
    public void setMotd(String motd) {
        this.motd = motd;
    }
    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public String getMotd() { return motd; }
    public JoinType getJoinType() {
        return joinType;
    }

    public enum JoinType {

        PLAYER(Lang.DEFAULT_PLAYER_GLOBAL_MESSAGE, Lang.DEFAULT_PLAYER_PLAYER_MESSAGE),
        SPECTATOR(Lang.DEFAULT_SPECTATOR_GLOBAL_MESSAGE, Lang.DEFAULT_SPECTATOR_PLAYER_MESSAGE),
        RECONNECT(Lang.DEFAULT_RECONNECT_GLOBAL_MESSAGE, Lang.DEFAULT_RECONNECT_PLAYER_MESSAGE),
        NOBODY(Lang.DEFAULT_NOBODY_GLOBAL_MESSAGE, Lang.DEFAULT_NOBODY_PLAYER_MESSAGE),
        ADMIN("ht.adminjoin", Lang.DEFAULT_ADMIN_GLOBAL_MESSAGE, Lang.DEFAULT_ADMIN_PLAYER_MESSAGE);

        private String permission;
        private String globalMessage;
        private String messageToPlayer;

        JoinType(String permission, String globalMessage, String messageToPlayer){
            this.permission = permission;this.globalMessage = globalMessage;
            this.messageToPlayer = messageToPlayer;
        }
        JoinType(String globalMessage, String messageToPlayer){
            this.globalMessage = globalMessage;
            this.messageToPlayer = messageToPlayer;
        }

        public String getMessageToPlayer() {
            return messageToPlayer;
        }

        public String getGlobalMessage() {
            return globalMessage;
        }

        public void setGlobalMessage(String globalMessage) {
            this.globalMessage = globalMessage;
        }

        public void setMessageToPlayer(String messageToPlayer) {
            this.messageToPlayer = messageToPlayer;
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

