package xmlswing.components.input.formatted;

import org.w3c.dom.Node;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.components.NodeFormEntry;
import xmlswing.components.form.FormEntry;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class FormattedNode extends AbstractNode<JFormattedTextField> {
    public FormattedNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JFormattedTextField getRootElement() {
        JFormattedTextField field = new JFormattedTextField();
        if(hasAttribute("type") && hasAttribute("format")) {
            switch (getAttribute("type")) {
                case "date":
                    DateFormat format = new SimpleDateFormat(getAttribute("format"));
                    DateFormatter formatter = new DateFormatter(format);
                    field = new JFormattedTextField(formatter);
                    break;
                case "number":
                    NumberFormat format1 = new DecimalFormat(getAttribute("format"));
                    field = new JFormattedTextField(format1);
                    break;
            }
        }
        field.setText(getNode().getTextContent());
        return field;
    }

    @Override
    public FormEntry<JFormattedTextField> asFormEntry() {
        return new NodeFormEntry<JFormattedTextField>(this) {
            @Override
            public void clearValue() {
                element.setText("");
            }

            @Override
            public Object getRawValue() {
                return element.getValue();
            }
        };
    }
}
