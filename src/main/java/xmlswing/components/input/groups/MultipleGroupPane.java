package xmlswing.components.input.groups;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MultipleGroupPane extends JPanel {
    private ArrayList<AbstractButton> abstractButtons = new ArrayList<>();

    public List<AbstractButton> getSelected() {
        return abstractButtons.stream().filter(AbstractButton::isSelected).collect(Collectors.toList());
    }

    public ArrayList<AbstractButton> getButtons() {
        return abstractButtons;
    }

    public void clear() {
        abstractButtons.forEach(abstractButton -> {
            if (abstractButton.isSelected()) {
                abstractButton.setSelected(false);
            }
        });
    }
}
