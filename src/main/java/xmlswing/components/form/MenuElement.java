package xmlswing.components.form;

import javax.swing.*;
import java.awt.*;

public class MenuElement extends FormEntry<JComboBox>{

    public MenuElement(String name, JComboBox value, boolean required) {
        super(name, value, required);
    }

    @Override
    public void clearValue() {

    }

    @Override
    public Object getRawValue() {
        return element.getSelectedItem();
    }
}
