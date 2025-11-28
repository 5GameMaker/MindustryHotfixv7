package mindustry.hotfix;

import mindustry.gen.Bullet;

import arc.util.CommandHandler;
import arc.util.CommandHandler.CommandResponse;
import mindustry.gen.*;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

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

    /**
     * Called when a tile changes its floor. Do not cache or use with a timer.
     * Do not modify any tiles inside listener code.
     * */
    public static class TileFloorChangeEvent {
        public Tile tile;
        public Floor previous, floor;

        public TileFloorChangeEvent set(Tile tile, Floor previous, Floor floor) {
            this.tile = tile;
            this.previous = previous;
            this.floor = floor;
            return this;
        }
    }

    /** A bullet has been created. Do not cache or use with a timer. */
    public static class BulletCreated {
        public Bullet bullet;
    }
}