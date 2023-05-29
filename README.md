# XMLSwing
XML based Swing UI elements for Java.\
The goal is to separate GUI and business logic as much as it is possible, reduce the time needed to create a basic interface without the need of "drag and drop" tools for GUI implementation
## List of components
Components are listed as it follows *XML tag (Swing Component)*

<table>
<tr>
<th>XML tag</th><th>Swing Element</th>
</tr>
<tr>
<td>Frame</td><td>JFrame</td>
</tr>
<tr>
<td>Button</td><td>JButton</td>
</tr>
<tr>
<td>CheckBox</td><td>JCheckBox</td>
</tr>
<tr>
<td>RadioButton</td><td>JRadioButton</td>
</tr>
<tr>
<td>Slider</td><td>JSlider</td>
</tr>
<tr>
<td>TextArea</td><td>JTextArea</td>
</tr>
<tr>
<td>TextField</td><td>JTextField</td>
</tr>
<tr>
<td>Label</td><td>JLabel</td>
</tr>
<tr>
<td>Border</td><td>JPanel with BorderLayout</td>
</tr>
<tr>
<td>Flow</td><td>JPanel with FlowLayout</td>
</tr>
<tr>
<td>GridBag</td><td>JPanel with GridBagLayout</td>
</tr>
<tr>
<td>Grid</td><td>JPanel with GridLayout</td>
</tr>
<tr>
<td>ScrollPane</td><td>JScrollPane</td>
</tr>
<tr>
<td>ComboBox</td><td>JComboBox</td>
</tr>
<tr>
<td>Table</td><td>JTable</td>
</tr>
<tr>
<td>TabPane</td><td>JTabbedPane</td>
</tr>
</table>

## Capabilities
XMLSwing allows you to:
- Retrieve elements by id (only for those elements with this attribute)
- Specify common and specific attributes directly in XML (width, height, background, etc...)
- Define and register your own tags for custom elements (create a customized factory is required in order to use this feature)

## Usage
Lifecycle:
1. Create XMLSwing object
2. Call the method `getRootComponent()`
3. *(Optional)* From the XMLSwing created object use `getRepository().obtain("component_id")` to retrieve objects within the root component. 


In Java code

```java
import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    public MyPanel() {
        setLayout(new GridLayout(3, 3, 5, 5));
        JLabel label = new JLabel("Label 1");
        JLabel label2 = new JLabel("Label 3");
        JButton button = new JButton("Click me!");
        add(label);
        add(label2);
        add(button);
    }
}
//More code ...
```
In XML
```xml
<Grid rows="3" columns="3" h-gap="5" v-gap="5">
    <Label>Label 1</Label>
    <Label>Label 3</Label>
    <Button>Click me!</Button>
</Grid>
```
