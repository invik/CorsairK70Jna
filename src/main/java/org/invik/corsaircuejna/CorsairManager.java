package org.invik.corsaircuejna;

import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.invik.corsaircuejna.jna.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary.JNACorsairGetLedPositionsByDeviceIndex;
import static org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary.JNACorsairSetLedsColors;

/**
 * Created by Antoine on 15/01/2017.
 */
public class CorsairManager {

    private static final CorsairManager INSTANCE = new CorsairManager();

    private CorsairManager() {

    }

    public static CorsairManager getInstance() {
        return INSTANCE;
    }

    public CorsairProtocolDetails initKeyboard() {
        final Pointer<JNACorsairProtocolDetails> corsairProtocolDetailsPointer = CorsairCUEJNAWrapperLibrary.JNACorsairPerformProtocolHandshake();
        final JNACorsairProtocolDetails jnaCorsairProtocolDetails = corsairProtocolDetailsPointer.get();
        final CorsairProtocolDetails corsairProtocolDetails = new CorsairProtocolDetails(jnaCorsairProtocolDetails);
        System.out.println("CorsairProtocolDetails: " + corsairProtocolDetails);
        // we release the associated memory with corsairProtocolDetailsPointer since this function is a wrapper
        // around Corsair SDK CorsairPerformProtocolHandshake function which returns a value.
        Pointer.release(corsairProtocolDetailsPointer);
        return corsairProtocolDetails;
    }

    public int deviceCount() {
        return CorsairCUEJNAWrapperLibrary.JNACorsairGetDeviceCount();
    }

    public int getKeyboardDeviceIndex() throws UnsupportedOperationException {
        final int deviceCount = this.deviceCount();
        for (int i = 0; i < deviceCount; i++) {
            final CorsairDeviceInfo corsairDeviceInfo = new CorsairDeviceInfo(i);
            if (corsairDeviceInfo.getType() == CorsairCUEJNAWrapperLibrary.CorsairDeviceType.CDT_Keyboard &&
                    (corsairDeviceInfo.getCapsMask() & (int) CorsairCUEJNAWrapperLibrary.CorsairDeviceCaps.CDC_Lighting.value()) != 0) {
                return i;
            }
        }
        throw new UnsupportedOperationException("Could not found any suitable device");
    }

    public CorsairDeviceInfo getKeyboardInfo(int deviceIndex) throws UnsupportedOperationException {
        final CorsairDeviceInfo corsairDeviceInfo = new CorsairDeviceInfo(deviceIndex);
        if (corsairDeviceInfo.getType() == CorsairCUEJNAWrapperLibrary.CorsairDeviceType.CDT_Keyboard &&
                (corsairDeviceInfo.getCapsMask() & (int) CorsairCUEJNAWrapperLibrary.CorsairDeviceCaps.CDC_Lighting.value()) != 0) {
            return corsairDeviceInfo;
        }
        throw new UnsupportedOperationException("Device with index " + deviceIndex + " does not support Lightning");
    }

    public Map<Long, CorsairLedPosition> getCorsairLedPositions(int deviceIndex) {
        final Map<Long, CorsairLedPosition> found = new HashMap<>();
        final JNACorsairLedPositions ledPositions = JNACorsairGetLedPositionsByDeviceIndex(deviceIndex).get();
        if (ledPositions != null && ledPositions.numberOfLed() > 0) {
            final Pointer<JNACorsairLedPosition> corsairLedPositionsPointer = ledPositions.pLedPosition();
            for (int i = 0; i < ledPositions.numberOfLed(); i++) {
                JNACorsairLedPosition jnaCorsairLedPosition = corsairLedPositionsPointer.apply(i);
                found.put(jnaCorsairLedPosition.ledId().value(), new CorsairLedPosition(jnaCorsairLedPosition));
            }
        }
        return found;
    }

    public CorsairCUEJNAWrapperLibrary.CorsairError getLastError() {
        IntValuedEnum<CorsairCUEJNAWrapperLibrary.CorsairError> corsairError = CorsairCUEJNAWrapperLibrary.JNACorsairGetLastError();
        return CorsairCUEJNAWrapperLibrary.CorsairError.getByValue(corsairError.value());
    }

    public List<CorsairLedPosition> sortLedPositionsFromTopLeftToBottomRight(Map<Long, CorsairLedPosition> ledPositionMap) {
        final List<CorsairLedPosition> values = new ArrayList<>();
        values.addAll(ledPositionMap.values());
        values.sort((o1, o2) -> {
            if (o1.getTop() > o2.getTop()) {
                return 1;
            }
            if (o1.getTop() < o2.getTop()) {
                return -1;
            }
            if (o1.getLeft() < o2.getLeft()) {
                return -1;
            }
            if (o1.getLeft() > o2.getLeft()) {
                return 1;
            }

            return 0;
        });
        return values;
    }

    public void applyLedPositionsColor(Color color, CorsairLedPosition... values) {
        Pointer<JNACorsairLedColor> corsairLedColors = Pointer.allocateArray(JNACorsairLedColor.class, values.length);
        for (int i = 0; i < values.length; i++) {
            CorsairLedPosition corsairLedPosition = values[i];
            JNACorsairLedColor jnaCorsairLedColor = new JNACorsairLedColor();
            jnaCorsairLedColor.ledId(corsairLedPosition.getCorsairLedId());
            setCorsairLedColorColor(color, jnaCorsairLedColor);
            corsairLedColors.set(i, jnaCorsairLedColor);
        }
        JNACorsairSetLedsColors(values.length, corsairLedColors);
        Pointer.release(corsairLedColors);
    }

    private void setCorsairLedColorColor(Color color, JNACorsairLedColor corsairLedColor) {
        corsairLedColor.r(color.getR());
        corsairLedColor.g(color.getG());
        corsairLedColor.b(color.getB());
    }

    public void setLedsColor(Color color, CorsairLedColor... corsairLedColors) {
        Pointer<JNACorsairLedColor> corsairLedColorsPointer = Pointer.allocateArray(JNACorsairLedColor.class, corsairLedColors.length);
        for (int i = 0; i < corsairLedColors.length; i++) {
            JNACorsairLedColor jnaCorsairLedColor = corsairLedColors[i].toJnaCorsairLedColor();
            jnaCorsairLedColor.r(color.getR());
            jnaCorsairLedColor.g(color.getG());
            jnaCorsairLedColor.b(color.getB());
            corsairLedColorsPointer.set(i, jnaCorsairLedColor);
        }
        JNACorsairSetLedsColors(corsairLedColors.length, corsairLedColorsPointer);
    }
}
