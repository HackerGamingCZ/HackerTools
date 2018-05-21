package cz.HackerGamingCZ.HackerTools.managers;

import java.util.HashMap;

public class CommandManager {

    private HashMap<Command, Boolean> commands = new HashMap<>();

    public CommandManager() {
        for (Command cmd : Command.values()) {
            commands.put(cmd, cmd.getDefaultValue());
        }
    }

    public boolean toggleCommand(Command cmd) {
        commands.replace(cmd, !commands.get(cmd));
        return !commands.get(cmd);
    }

    /**
     * @param command to take from HashMap
     * @return value from HashMap or false when Hashmap doesn't contains that key
     **/
    public boolean isCommandEnabled(Command command) {
        return commands.getOrDefault(command, false);
    }

    public enum Command {

        DEBUG(true),
        WATCHDOGMODE(false);

        private boolean defaultValue;

        Command(boolean defaultValue) {
            this.defaultValue = defaultValue;
        }

        public boolean getDefaultValue() {
            return defaultValue;
        }
    }
}