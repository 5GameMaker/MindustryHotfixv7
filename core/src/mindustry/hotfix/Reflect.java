package mindustry.hotfix;

import arc.util.io.ReusableByteOutStream;
import mindustry.core.NetServer;

import java.io.DataOutputStream;

/** Access to private fields of Mindustry classes */
public class Reflect {
    private Reflect() {}

    /** Data stream for writing player sync data to. */
    public DataOutputStream dataStreamOf(NetServer server) {
        return server.dataStream;
    }

    public ReusableByteOutStream syncStreamOf(NetServer server) {
        return server.syncStream;
    }
}
