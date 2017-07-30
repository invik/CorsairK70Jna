package org.invik.corsaircuejna;

import org.invik.corsaircuejna.jna.CorsairCUEJNAWrapperLibrary;
import org.invik.corsaircuejna.jna.JNACorsairLedColor;

/**
 * Created by Antoine on 15/01/2017.
 */
public class CorsairLedColor {

    private CorsairCUEJNAWrapperLibrary.CorsairLedId ledId;
    private int r;
    private int g;
    private int b;

    public CorsairLedColor(JNACorsairLedColor jnaCorsairLedColor) {
        this.ledId = CorsairCUEJNAWrapperLibrary.CorsairLedId.getByValue(jnaCorsairLedColor.ledId().value());
        this.r = jnaCorsairLedColor.r();
        this.g = jnaCorsairLedColor.g();
        this.b = jnaCorsairLedColor.b();
    }

    public CorsairLedColor(CorsairCUEJNAWrapperLibrary.CorsairLedId ledId, int r, int g, int b) {
        this.ledId = ledId;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public CorsairLedColor(CorsairCUEJNAWrapperLibrary.CorsairLedId ledId) {
        this.ledId = ledId;
    }

    public JNACorsairLedColor toJnaCorsairLedColor() {
        JNACorsairLedColor jnaCorsairLedColor = new JNACorsairLedColor();
        jnaCorsairLedColor.ledId(this.ledId);
        jnaCorsairLedColor.r(this.r);
        jnaCorsairLedColor.g(this.g);
        jnaCorsairLedColor.b(this.b);
        return jnaCorsairLedColor;
    }

    public CorsairCUEJNAWrapperLibrary.CorsairLedId getLedId() {
        return ledId;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }
}


