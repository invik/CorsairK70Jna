package org.invik.corsaircuejna;

import org.bridj.Pointer;
import org.invik.corsaircuejna.jna.JNACorsairLedPosition;
import org.invik.corsaircuejna.jna.JNACorsairLedPositions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 15/01/2017.
 */
public class CorsairLedPositions {

    private List<CorsairLedPosition> corsairLedPositionList;
    private int numberOfLeds;

    public CorsairLedPositions(JNACorsairLedPositions jnaCorsairLedPositions) {
        this.corsairLedPositionList = new ArrayList<>();
        this.numberOfLeds = jnaCorsairLedPositions.numberOfLed();
        final Pointer<JNACorsairLedPosition> corsairLedPositionsPointer = jnaCorsairLedPositions.pLedPosition();
        for (int i = 0; i < jnaCorsairLedPositions.numberOfLed(); i++) {
            final JNACorsairLedPosition jnaCorsairLedPosition = corsairLedPositionsPointer.apply(i);
            this.corsairLedPositionList.add(new CorsairLedPosition(jnaCorsairLedPosition));
        }
    }

    public List<CorsairLedPosition> getCorsairLedPositionList() {
        return corsairLedPositionList;
    }

    public int getNumberOfLeds() {
        return numberOfLeds;
    }
}
