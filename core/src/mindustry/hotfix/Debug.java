package mindustry.hotfix;

import arc.struct.Seq;
import mindustry.Vars;
import mindustry.gen.Player;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class Debug {
    private Debug() {}

    private static final Seq<WeakReference<Player>> reportsEnabledFor = new Seq<>(4);
    public static boolean debuggingEnabled = false;

    public static void report(Throwable error) {
        if (!debuggingEnabled) return;

        StringWriter tmp = new StringWriter();
        PrintWriter s = new PrintWriter(tmp);
        error.printStackTrace(s);
        s.close();
        String serr = tmp.toString();
        reportsEnabledFor.retainAll(player -> {
            var p = player.get();
            if (p == null) return false;
            p.sendUnformatted(serr);
            return true;
        });
    }

    public static void load() {
        // TODO: Remove this getenv bullshit.
        if (Objects.equals(System.getenv("MINDUSTRY_HOTFIX_DEBUG"), "1")) debuggingEnabled = true;
        else return;

        // Toggle error reports.
        Vars.netServer.addPacketHandler("hotfix.debug/reports.toggle", (player, txt) -> {
            if (!player.admin || !debuggingEnabled) return;

            if (!reportsEnabledFor.remove(x -> x.get() == player)) {
                reportsEnabledFor.add(new WeakReference<>(player));
            }
        });

        // Debug logic.
        Vars.netServer.addPacketHandler("hotfix.debug/logic", (player, txt) -> {
            if (!player.admin || !debuggingEnabled) return;
        });
    }
}
