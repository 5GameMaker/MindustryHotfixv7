package mindustry.hotfix;

import arc.util.CommandHandler;
import arc.util.CommandHandler.CommandResponse;
import mindustry.gen.*;

/**
 * Events specific to MindustryHotfix. Only depend on this API if you're not planning
 * to use any other server software.
 */
public class Events {
    private Events() {}

    /**
     * Called after a player executes a command.
     */
    public static class CommandExecuted {
        public Player player;
        public CommandResponse response;
        public CommandHandler handler;
        public CommandExecuted(Player player, CommandResponse response, CommandHandler handler) {
            this.player = player;
            this.response = response;
            this.handler = handler;
        }
    }

    /**
     * Called after console executes a command.
     */
    public static class ConsoleCommandExecuted {
        public CommandResponse response;
        public CommandHandler handler;
        public Boolean cancel;

        public ConsoleCommandExecuted(CommandResponse response, CommandHandler handler) {
            this.response = response;
            this.handler = handler;
        }
    }
}