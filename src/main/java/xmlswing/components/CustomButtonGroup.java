package xmlswing.components;

import javax.swing.*;
import java.awt.*;

public class CustomButtonGroup extends Component {
    private ButtonGroup buttonGroup;
    private AbstractButton selected;

    public CustomButtonGroup() {
        buttonGroup = new ButtonGroup();
    }

    public void add(AbstractButton button) {
        buttonGroup.add(button);
        button.addActionListener(a -> {
            selected = button;
        });
    }

    public AbstractButton getSelected() {
        return selected;
    }
}
