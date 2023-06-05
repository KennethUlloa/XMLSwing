package xmlswing.components.input.groups;

import javax.swing.*;
import java.util.Enumeration;

public class SingleGroupPane extends JPanel {
    private ButtonGroup group = new ButtonGroup();

    public ButtonGroup getGroup() {
        return group;
    }

    public AbstractButton getSelected() {
        for(Enumeration<AbstractButton> buttons = group.getElements(); group.getElements().hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if(button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    public void clear() {
        for(Enumeration<AbstractButton> buttons = group.getElements(); group.getElements().hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if(button.isSelected()) {
                button.setSelected(false);
            }
        }
    }
}
