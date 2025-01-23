package mindustry.net;

import arc.util.io.*;
import mindustry.hotfix.ConnectionStage;

import java.io.*;

public abstract class Packet{
    //internally used by generated code
    protected static final byte[] NODATA = {};
    protected static final ReusableByteInStream BAIS = new ReusableByteInStream();
    protected static final Reads READ = new Reads(new DataInputStream(BAIS));

    //these are constants because I don't want to bother making an enum to mirror the annotation enum

    /** Does not get handled unless client is connected. */
    public static final int priorityLow = 0;
    /** Gets put in a queue and processed if not connected. */
    public static final int priorityNormal = 1;
    /** Gets handled immediately, regardless of connection status. */
    public static final int priorityHigh = 2;

    public void read(Reads read){}
    public void write(Writes write){}

    public void read(Reads read, int length){
        read(read);
    }

    public void handled(){}

    public int getPriority(){
        return priorityNormal;
    }

    public ConnectionStage getRequiredStage() {
        if(this.getClass() == mindustry.gen.ConnectConfirmCallPacket.class) return ConnectionStage.BeganConnecting;
        if(this.getClass() == mindustry.gen.ClientSnapshotCallPacket.class) return ConnectionStage.BeganConnecting;
        if(this.getClass() == mindustry.gen.PingCallPacket.class) return ConnectionStage.BeganConnecting;
        return ConnectionStage.Play;
    }

    public ConnectionStage getMaxStage() {
        if(this.getClass() == mindustry.gen.ConnectConfirmCallPacket.class) return ConnectionStage.Play;
        if(this.getClass() == mindustry.gen.ClientSnapshotCallPacket.class) return ConnectionStage.Play;
        if(this.getClass() == mindustry.gen.PingCallPacket.class) return ConnectionStage.Play;
        return getRequiredStage();
    }

    public void handleClient(){}
    public void handleServer(NetConnection con){}
}
