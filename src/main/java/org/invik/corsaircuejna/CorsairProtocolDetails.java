package org.invik.corsaircuejna;

import org.bridj.Pointer;
import org.invik.corsaircuejna.jna.JNACorsairProtocolDetails;

/**
 * Created by Antoine on 15/01/2017.
 */
public class CorsairProtocolDetails {

    private String sdkVersion;
    private String serverVersion;
    private int sdkProtocolVersion;
    private int serverProtocolVersion;
    private boolean breakingChanges;

    public CorsairProtocolDetails(JNACorsairProtocolDetails jnaCorsairProtocolDetails) {
        this.sdkVersion = jnaCorsairProtocolDetails.sdkVersion().getString(Pointer.StringType.C);
        this.serverVersion = jnaCorsairProtocolDetails.serverVersion().getString(Pointer.StringType.C);
        this.sdkProtocolVersion = jnaCorsairProtocolDetails.sdkProtocolVersion();
        this.serverProtocolVersion = jnaCorsairProtocolDetails.serverProtocolVersion();
        this.breakingChanges = jnaCorsairProtocolDetails.breakingChanges();
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public int getSdkProtocolVersion() {
        return sdkProtocolVersion;
    }

    public int getServerProtocolVersion() {
        return serverProtocolVersion;
    }

    public boolean isBreakingChanges() {
        return breakingChanges;
    }

    @Override
    public String toString() {
        return "CorsairProtocolDetails{" +
                "sdkVersion='" + sdkVersion + '\'' +
                ", serverVersion='" + serverVersion + '\'' +
                ", sdkProtocolVersion=" + sdkProtocolVersion +
                ", serverProtocolVersion=" + serverProtocolVersion +
                ", breakingChanges=" + breakingChanges +
                '}';
    }
}
