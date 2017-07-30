package org.invik.corsaircuejna;

import org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary;
import org.invik.corsaircuejna.jna.JNACorsairLedPosition;

/**
 * Created by Antoine on 15/01/2017.
 */
public class CorsairLedPosition {

    private CorsairCUEJNAWrapperLibrary.CorsairLedId corsairLedId;
    private double top;
    private double left;
    private double height;
    private double width;

    public CorsairLedPosition(final JNACorsairLedPosition jnaCorsairLedPosition) {
        this.corsairLedId = CorsairCUEJNAWrapperLibrary.CorsairLedId.getByValue(jnaCorsairLedPosition.ledId().value());
        this.top = jnaCorsairLedPosition.top();
        this.left = jnaCorsairLedPosition.left();
        this.height = jnaCorsairLedPosition.height();
        this.width = jnaCorsairLedPosition.width();
    }

    public CorsairLedPosition(CorsairCUEJNAWrapperLibrary.CorsairLedId corsairLedId) {
        this.corsairLedId = corsairLedId;
    }

    public CorsairCUEJNAWrapperLibrary.CorsairLedId getCorsairLedId() {
        return corsairLedId;
    }

    public double getTop() {
        return top;
    }

    public double getLeft() {
        return left;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
