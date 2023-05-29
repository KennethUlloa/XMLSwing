package xmlswing.components;

import xmlswing.types.TypeNode;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static xmlswing.components.CommonProperties.getColor;

/**
 * <h3>Properties</h3>
 * <ul>
 *     <li>color: foreground</li>
 *     <li>padding: outer space</li>
 *     <li>
 *         border: none or three tuple
 *          <ul>
 *              <li>width: integer</li>
 *              <li>style: (solid, dashed, double)</li>
 *              <li>color: hex</li>
 *          </ul>
 *      </li>
 *      <li>font-style: bold, italic</li>
 *      <li>font-size: integer</li>
 *      <li>font-family: font name</li>
 *      <li>width: integer</li>
 *      <li>height: integer</li>
 *      <li>x: position </li>
 *      <li>y: position </li>
 *      <li>pref-width</li>
 *      <li>pref-height</li>
 *      <li>min-width</li>
 *      <li>min-height</li>
 *      <li>max-width</li>
 *      <li>max-height</li>
 *      <li>background: none or hex </li>
 *
 * </ul>
 */
public class PropertiesReader {
    private TypeNode<Component> node;

    public PropertiesReader(TypeNode<Component> node) {
        this.node = node;
    }

    public static void setUpComponent(TypeNode<Component> typeNode) {
        PropertiesReader propertiesReader = new PropertiesReader(typeNode);
        propertiesReader.setUpComponent();
    }

    public void setUpComponent() {
        setPosition();
        setSize();
        setPreferredSize();
        setMaximumSize();
        setMinimumSize();
        setFontSize();
        setFontStyle();
        setFontFamily();
        setBackgroundColor();
        setBorder();
        setPadding();
        setForeground();

    }

    public void setForeground() {
        if(!node.hasAttribute("color")) return;
        Component component = node.getObject();
        component.setForeground(getColor(node.getAttribute("color")));
    }

    public void setPadding() {
        if(!node.hasAttribute("padding") || !(node.getObject() instanceof JComponent)) return;
        JComponent component = (JComponent) node.getObject();
        Insets insets = CommonProperties.insetsFromString(node.getAttribute("padding"));
        Border outBorder = component.getBorder();
        Border emptyBorder = new EmptyBorder(insets);
        if(outBorder == null) {
            component.setBorder(emptyBorder);
        }else{
            component.setBorder(new CompoundBorder(outBorder, emptyBorder));
        }
    }

    public void setBorder() {
        if(!node.hasAttribute("border") || !(node.getObject() instanceof JComponent)) return;
        JComponent component = (JComponent) node.getObject();
        if(node.getAttribute("border").equals("none")){
            component.setBorder(null);
            return;
        }
        String[] parameters = node.getAttribute("border").trim().split(" ");
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

    public void setFontStyle() {
        if(!node.hasAttribute("font-style")) return;
        int op = Font.PLAIN;
        switch (node.getAttribute("font-style")) {
            case "bold": op = Font.BOLD; break;
            case "italic": op = Font.ITALIC; break;
        }
        Component component = node.getObject();
        component.setFont(component.getFont().deriveFont(op));
    }

    public void setFontSize() {
        if(!node.hasAttribute("font-size")) return;
        float size = Float.parseFloat(node.getAttribute("font-size"));
        Component component = node.getObject();
        component.setFont(component.getFont().deriveFont(size));
    }

    public void setFontFamily() {
        if(!node.hasAttribute("font-family")) return;
        Component component = node.getObject();
        Font f = new Font(node.getAttribute("font-family"), component.getFont().getStyle(), component.getFont().getSize());
        component.setFont(f);
    }

    public void setSize() {
        Component component = node.getObject();
        if(node.hasAttribute("width")) {
            try {component.setSize(Integer.parseInt(node.getAttribute("width")), component.getHeight());
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("height")) {
            try {component.setSize(component.getWidth(), Integer.parseInt(node.getAttribute("height")));
            } catch (NumberFormatException ignored) {}
        }
    }

    public void setPosition() {
        Component component = node.getObject();
        if(node.hasAttribute("x")){
            try {component.setLocation(Integer.parseInt(node.getAttribute("x")), component.getY());
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("y")) {
            try {component.setLocation(component.getX(), Integer.parseInt(node.getAttribute("y")));
            } catch (NumberFormatException ignored) {}
        }
    }

    public void setPreferredSize() {
        Component component = node.getObject();
        if(node.hasAttribute("pref-width")) {
            try {component.setPreferredSize(new Dimension(
                    Integer.parseInt(node.getAttribute("pref-width")), component.getPreferredSize().height));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("pref-height")) {
            try {component.setPreferredSize(new Dimension(
                    component.getPreferredSize().width, Integer.parseInt(node.getAttribute("pref-height"))));
            } catch (NumberFormatException ignored) {}
        }
    }

    public void setMinimumSize() {
        Component component = node.getObject();
        if(node.hasAttribute("min-width")) {
            try {component.setMinimumSize(new Dimension(
                    Integer.parseInt(node.getAttribute("min-width")), component.getMinimumSize().height));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("min-height")) {
            try {component.setMinimumSize(new Dimension(
                    component.getMinimumSize().width, Integer.parseInt(node.getAttribute("min-height"))));
            } catch (NumberFormatException ignored) {}
        }
    }

    public void setMaximumSize() {
        Component component = node.getObject();
        if(node.hasAttribute("max-width")) {
            try {component.setMaximumSize(new Dimension(
                    Integer.parseInt(node.getAttribute("max-width")), component.getMaximumSize().height));
            } catch (NumberFormatException ignored) {}
        }
        if(node.hasAttribute("max-height")) {
            try {component.setMaximumSize(new Dimension(
                    component.getMaximumSize().width, Integer.parseInt(node.getAttribute("max-height"))));
            } catch (NumberFormatException ignored) {}
        }
    }

    public void setBackgroundColor() {
        if (!node.hasAttribute("background") || !(node.getObject() instanceof JComponent)) return;
        String colorString = node.getAttribute("background");
        JComponent component = (JComponent) node.getObject();
        if(colorString.equals("transparent")){
            component.setOpaque(false);
        }else{
            component.setOpaque(true);
            component.setBackground(getColor(colorString));
        }

    }

}
