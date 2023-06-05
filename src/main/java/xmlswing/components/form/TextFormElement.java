package xmlswing.components.form;

import javax.swing.text.JTextComponent;

public class TextFormElement extends FormEntry<JTextComponent> {
    public TextFormElement(String name, JTextComponent value, boolean required) {
        super(name, value, required);
    }

    @Override
    public void clearValue() {

    }

    @Override
    public Object getRawValue() {
        return element.getText();
    }
}
