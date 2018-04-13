package cz.HackerGamingCZ.HackerTools.enums;

public enum GameState {

    SETUP("", JoinType.ADMIN),
    WAITING("", JoinType.PLAYER),
    STARTING("", JoinType.PLAYER),
    INGAME("", JoinType.SPECTATOR),
    END("", JoinType.NOBODY),
    RESET("", JoinType.NOBODY);

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

    public String getMotd() {
        return motd;
    }
    public JoinType getJoinType() {
        return joinType;
    }

    public enum JoinType {

        PLAYER(""),
        SPECTATOR(""),
        NOBODY(""),
        ADMIN("", "adminjoin");

        private String message;
        private String permission;

        JoinType(String message){
            this.message = message;
        }

        JoinType(String message, String permission){
            this.message = message;
            this.permission = permission;
        }

        //SET & GET
        public String getMessage() {
            return message;
        }
        public String getPermission() {
            return permission;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public void setPermission(String permission) {
            this.permission = permission;
        }
    }

}

