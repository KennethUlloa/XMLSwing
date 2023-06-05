package xmlswing.components.form;

import javax.swing.*;

public class SpinerElement extends FormEntry<JSpinner>{
    public SpinerElement(String name, JSpinner value, boolean required) {
        super(name, value, required);
    }

    @Override
    public void clearValue() {

    }

    @Override
    public Object getRawValue() {
        return element.getValue();
    }
}
