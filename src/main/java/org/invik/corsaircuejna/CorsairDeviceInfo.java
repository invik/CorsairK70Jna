package org.invik.corsaircuejna;

import org.bridj.Pointer;
import org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary;
import org.invik.corsaircuejna.jna.JNACorsairDeviceInfo;

import static org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary.JNACorsairGetDeviceInfo;

/**
 * Created by Antoine on 15/01/2017.
 */
public class CorsairDeviceInfo {

    private CorsairCUEJNAWrapperLibrary.CorsairDeviceType type;
    private String model;
    private CorsairCUEJNAWrapperLibrary.CorsairPhysicalLayout physicalLayout;
    private CorsairCUEJNAWrapperLibrary.CorsairLogicalLayout logicalLayout;
    private int capsMask;
    private int ledsCount;

    public CorsairDeviceInfo(JNACorsairDeviceInfo jnaCorsairDeviceInfo) {
        this.setValues(jnaCorsairDeviceInfo);
    }

    public CorsairDeviceInfo(int deviceId) {
        final Pointer<JNACorsairDeviceInfo> jnaCorsairDeviceInfoPointer = JNACorsairGetDeviceInfo(deviceId);
        final JNACorsairDeviceInfo jnaCorsairDeviceInfo = jnaCorsairDeviceInfoPointer.get();
        this.setValues(jnaCorsairDeviceInfo);
        Pointer.release(jnaCorsairDeviceInfoPointer);
    }

    private void setValues(JNACorsairDeviceInfo jnaCorsairDeviceInfo) {
        this.type = CorsairCUEJNAWrapperLibrary.CorsairDeviceType.getByValue(jnaCorsairDeviceInfo.type().value());
        this.model = jnaCorsairDeviceInfo.model().getString(Pointer.StringType.C);
        this.physicalLayout = CorsairCUEJNAWrapperLibrary.CorsairPhysicalLayout.getByValue(jnaCorsairDeviceInfo.physicalLayout().value());
        this.logicalLayout = CorsairCUEJNAWrapperLibrary.CorsairLogicalLayout.getByValue(jnaCorsairDeviceInfo.logicalLayout().value());
        this.capsMask = jnaCorsairDeviceInfo.capsMask();
        this.ledsCount = jnaCorsairDeviceInfo.ledsCount();
    }

    public CorsairCUEJNAWrapperLibrary.CorsairDeviceType getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public CorsairCUEJNAWrapperLibrary.CorsairPhysicalLayout getPhysicalLayout() {
        return physicalLayout;
    }

    public CorsairCUEJNAWrapperLibrary.CorsairLogicalLayout getLogicalLayout() {
        return logicalLayout;
    }

    public int getCapsMask() {
        return capsMask;
    }

    public int getLedsCount() {
        return ledsCount;
    }

    @Override
    public String toString() {
        return "CorsairDeviceInfo{" +
                "type=" + type +
                ", model='" + model + '\'' +
                ", physicalLayout=" + physicalLayout +
                ", logicalLayout=" + logicalLayout +
                ", capsMask=" + capsMask +
                ", ledsCount=" + ledsCount +
                '}';
    }
}
