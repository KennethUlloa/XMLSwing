package mocking;

import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.HashMap;

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

    public static void setUpComponent(JComponent component, Node node) {
        HashMap<String, String> styles = readStyle(node);
        System.out.println(styles);
        setPosition(component, styles);
        setSize(component, styles);
        setPreferredSize(component, styles);
        setMaximumSize(component, styles);
        setMinimumSize(component, styles);
        setFontSize(component, styles);
        setFontStyle(component, styles);
        setFontFamily(component, styles);
        setBackgroundColor(component, styles);
        setBorder(component, styles);
        setPadding(component, styles);
        setForeground(component, styles);

    }

    public static void setForeground(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("color")) return;
        component.setForeground(getColor(styles.get("color")));
    }

    public static void setPadding(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("padding")) return;
        Insets insets = insetsFromString(styles.get("padding"));
        Border outBorder = component.getBorder();
        Border emptyBorder = new EmptyBorder(insets);
        if(outBorder == null) {
            component.setBorder(emptyBorder);
        }else{
            component.setBorder(new CompoundBorder(outBorder, emptyBorder));
        }
    }

    public static HashMap<String, String> readStyle(Node node) {
        HashMap<String,String> styles = new HashMap<>();
        String property = MockMaker.getAttributeValue(node, "style");
        if(property == null) return styles;
        property = property.trim();
        String[] values = property.split("(;)|(\n)");
        for(String prop : values) {
            String[] line = prop.split(":");
            if(line.length >= 2) {
                styles.put(line[0].trim(), line[1].trim());
            }
        }
        return styles;
    }

    public static void setBorder(JComponent component, HashMap<String, String> styles) {
        //String property = MockMaker.getAttributeValue(node, "border");
        if(!styles.containsKey("border")) return;
        if(styles.get("border").equals("none")){
            component.setBorder(null);
            return;
        }
        String[] parameters = styles.get("border").trim().split(" ");
        if(parameters.length != 3) return;
        int width = Integer.parseInt(parameters[0]);
        Color color = getColor(parameters[2]);
        switch (parameters[1]) {
            case "solid": component.setBorder(new LineBorder(color, width)); break;
            case "dashed": component.setBorder(BorderFactory.createDashedBorder(color, width, width)); break;
            case "double": component.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(color, width),
                    new CompoundBorder(
                            new EmptyBorder(width, width, width, width),
                            new LineBorder(color, width)
                    )
                    )); break;
        }
    }
    public static void setFontStyle(JComponent component, HashMap<String, String> styles) {
        //String property = MockMaker.getAttributeValue(node, "font-style");
        //if (property == null) return;
        if(!styles.containsKey("font-style")) return;
        int op = Font.PLAIN;
        switch (styles.get("font-style")) {
            case "bold": op = Font.BOLD; break;
            case "italic": op = Font.ITALIC; break;
        }
        component.setFont(component.getFont().deriveFont(op));
    }
    public static void setFontSize(JComponent component, HashMap<String, String> styles) {
        //String property = MockMaker.getAttributeValue(node, "font-size");
        if(!styles.containsKey("font-size")) return;
        //if (property == null) return;
        float size = Float.parseFloat(styles.get("font-size"));
        component.setFont(component.getFont().deriveFont(size));
    }

    public static void setFontFamily(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("font-family")) return;
        Font f = new Font(styles.get("font-family"), component.getFont().getStyle(), component.getFont().getSize());
        System.out.println(f);
        component.setFont(f);
    }

    public static void setSize(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("width")) return;
        try {component.setSize(Integer.parseInt(styles.get("width")), component.getHeight());
        } catch (NumberFormatException ignored) {}
        if(!styles.containsKey("height")) return;
        try {component.setSize(component.getWidth(), Integer.parseInt(styles.get("height")));
        } catch (NumberFormatException ignored) {}
    }
    public static void setPosition(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("x")) return;
        try {component.setLocation(Integer.parseInt(styles.get("x")), component.getY());
        } catch (NumberFormatException ignored) {}
        if(!styles.containsKey("y")) return;
        try {component.setLocation(component.getX(), Integer.parseInt(styles.get("y")));
        } catch (NumberFormatException ignored) {}

    }

    public static void setPreferredSize(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("pref-width")) return;
        try {component.setPreferredSize(new Dimension(
                Integer.parseInt(styles.get("pref-width")), component.getPreferredSize().height));
        } catch (NumberFormatException ignored) {}
        if(!styles.containsKey("pref-height")) return;
        try {component.setPreferredSize(new Dimension(
                component.getPreferredSize().width, Integer.parseInt(styles.get("pref-height"))));
        } catch (NumberFormatException ignored) {}
    }

    public static void setMinimumSize(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("min-width")) return;
        try {component.setMinimumSize(new Dimension(
                Integer.parseInt(styles.get("min-width")), component.getMinimumSize().height));
        } catch (NumberFormatException ignored) {}
        if(!styles.containsKey("min-height")) return;
        try {component.setMinimumSize(new Dimension(
                component.getMinimumSize().width, Integer.parseInt(styles.get("min-height"))));
        } catch (NumberFormatException ignored) {}
    }

    public static void setMaximumSize(JComponent component, HashMap<String, String> styles) {
        if(!styles.containsKey("max-width")) return;
        try {component.setMaximumSize(new Dimension(
                Integer.parseInt(styles.get("max-width")), component.getMaximumSize().height));
        } catch (NumberFormatException ignored) {}
        if(!styles.containsKey("max-height")) return;
        try {component.setMaximumSize(new Dimension(
                component.getMaximumSize().width, Integer.parseInt(styles.get("max-height"))));
        } catch (NumberFormatException ignored) {}
    }

    public static void setBackgroundColor(JComponent component, HashMap<String, String> styles) {
        if (!styles.containsKey("background")) return;
        String colorString = styles.get("background");
        if(colorString.equals("transparent")){
            component.setOpaque(false);
        }else{
            component.setOpaque(true);
            component.setBackground(getColor(colorString));
        }

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
