package mindustry.hotfix;

import mindustry.gen.*;
import mindustry.net.NetConnection;
import mindustry.net.Packet;

public enum ConnectionStage {
    PreConnect,
    BeganConnecting,
    Play,
    Disconnected,

    ;;

    public static ConnectionStage of(Player player) {
        if (player == null) return PreConnect;
        return player.con.stage();
    }

    public static ConnectionStage of(NetConnection con) {
        if (con == null) return PreConnect;
        return con.stage();
    }

    public static ConnectionStage of(Packet packet) {
        return packet.getRequiredStage();
    }

    public static ConnectionStage maxOf(Packet packet) {
        return packet.getMaxStage();
    }

    public boolean lt(ConnectionStage other) {
        return this.ordinal() < other.ordinal();
    }

    public boolean gt(ConnectionStage other) {
        return this.ordinal() > other.ordinal();
    }

    public boolean between(ConnectionStage min, ConnectionStage max) {
        if (max.lt(min)) return between(max, min);
        return !min.gt(this) && !max.lt(this);
    }
}
