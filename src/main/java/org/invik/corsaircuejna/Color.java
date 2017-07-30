package org.invik.corsaircuejna;

/**
 * Created by Antoine on 15/01/2017.
 */
public class Color {

    public static final Color OFF = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    private int r;
    private int g;
    private int b;

    public Color(int r, int g, int b) throws IllegalArgumentException {
        if (r > 255 || g > 255 || b > 255) {
            throw new IllegalArgumentException("Color values can not exceed 255");
        }
        this.r = r;
        this.g = g;
        this.b = b;
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
}
