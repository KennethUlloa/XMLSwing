package xmlswing.components;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * Color:
 * <ul>
 *      <li>yellow</li>
 *      <li>blue</li>
 *      <li>red</li>
 *      <li>green</li>
 *      <li>orange</li>
 *      <li>gray</li>
 *      <li>lightgray</li>
 *      <li>cyan</li>
 *      <li>darkgray</li>
 *      <li>black</li>
 *      <li>white</li>
 *      <li>magenta</li>
 *      <li>pink</li>
 * </ul>
 */
public class CommonProperties {
    public static Dimension dimensionFromString(String string) {
        return dimensionFromString(string, false);
    }

    public static Dimension dimensionFromString(String string, boolean nullable) {
        int w = 0, h = 0;
        String[] items = string.split(" ");
        if (items.length == 0) return (nullable)? null : new Dimension(0,0);
        int[] dim = new int[items.length];
        for(int i = 0 ; i < items.length ; i++) {
            try {
                dim[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException ignored) {}
        }
        if(dim.length >= 2) {
            w = dim[0];
            h = dim[1];
        }
        if(dim.length == 1) {
            w = dim[0];
            h = w;
        }
        return new Dimension(w,h);
    }

    public static Insets insetsFromString(String string) {
        String[] items = string.split(" ");
        if (items.length == 0) return new Insets(0,0,0,0);
        int[] dim = new int[Math.min(items.length, 4)];
        for(int i = 0 ; i < dim.length ; i++) {
            try {
                dim[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException ignored) {}
        }
        if(dim.length == 1) {
            return new Insets(dim[0], dim[0], dim[0], dim[0]);
        }
        if(dim.length == 2) {
            return new Insets(dim[0], dim[1], dim[0], dim[1]);
        }
        if(dim.length == 3) {
            return new Insets(dim[0], dim[1], dim[2], dim[1]);
        }

        return new Insets(dim[0], dim[1], dim[2], dim[3]);
    }
    public static Color getColor(String string) {
        switch (string) {
            case "yellow" : return Color.YELLOW;
            case "blue": return Color.BLUE;
            case "red": return Color.RED;
            case "green": return Color.GREEN;
            case "orange": return Color.ORANGE;
            case "gray": return Color.GRAY;
            case "lightgray": return Color.LIGHT_GRAY;
            case "cyan": return Color.CYAN;
            case "darkgray": return Color.DARK_GRAY;
            case "black": return Color.BLACK;
            case "white": return Color.WHITE;
            case "magenta": return Color.MAGENTA;
            case "pink": return Color.PINK;
            default: return Color.decode(string);
        }
    }
}
