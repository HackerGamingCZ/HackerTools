package cz.HackerGamingCZ.HackerTools.enums;

import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.config.SimpleConfig;

public enum GameState {

    SETUP("§cSetup mode", JoinType.ADMIN),
    WAITING("§cGame is waiting for players...", JoinType.PLAYER),
    STARTING("§cGame is starting...", JoinType.PLAYER),
    INGAME("§cGame is in progress", JoinType.SPECTATOR),
    END("§cGame is over", JoinType.NOBODY),
    RESET("§cGame is resetting...", JoinType.NOBODY),
    NONE("", JoinType.NONE);

    private String motd;
    private JoinType joinType;

    GameState(String motd, JoinType joinType) {
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

    public String getMotd() {
        return motd;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public enum JoinType {

        PLAYER(),
        SPECTATOR(),
        RECONNECT(),
        NOBODY(),
        ADMIN("ht.adminjoin"),
        NONE();

        private String permission;
        private String globalMessage;
        private String messageToPlayer;

        JoinType(String permission) {
            this.permission = permission;
        }

        JoinType() {
        }

        public void setupMessage() {
            SimpleConfig config = HackerTools.getPlugin().getHtConfigManager().getLang();
            String playerConfig = "default-" + toString().toLowerCase() + "-player-message";
            String globalConfig = "default-" + toString().toLowerCase() + "-global-message";
            this.messageToPlayer = config.getString(playerConfig);
            this.globalMessage = config.getString(globalConfig);
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

