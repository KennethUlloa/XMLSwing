package xmlswing.components;

import javax.swing.*;
import java.awt.*;

public class CustomButtonGroup extends Component {
    private ButtonGroup buttonGroup;

    public CustomButtonGroup() {
        buttonGroup = new ButtonGroup();
    }

    public void add(AbstractButton button) {
        buttonGroup.add(button);
    }
}
