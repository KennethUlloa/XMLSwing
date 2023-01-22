# XMLSwing
XML based Swing UI elements for Java.\
The goal is to separate GUI and business logic as much as it is possible, reduce the time needed to create a basic interface without the need of "drag and drop" tools for GUI implementation
## List of components
Components are listed as it follows *XML tag (Swing Component)*
Boxed
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
</table>

## Capabilities
XMLSwing allows you to:
- Retrieve elements by id (only for those elements with this attribute)
- Specify common and specific attributes directly in XML (width, height, background, etc...)
- Define and register your own tags for custom elements (create a customized factory is required in order to use this feature)
- Parse simple variables in your XML following the next format: {#variable_name#}
## Usage
Lifecycle:
1. Create MockFactoy object
2. Register custom factory objects *(optional)*
3. Generate the frame
4. Retrieve objects by id *(optional)*

In Java code
```java
MockFactory mockMaker = new MockFactory(Files.newInputStream(Paths.get("./afile.xml"))); //This accepts InputStream objects
//Before the actual construction of GUI if you're going to use custom element factory you
//must register it with MockElementFactory.register("tag",custom_element_factory_instance);
//Example: MockElementFactory("Button", new ButtonFactory()); <- this will parse <Button> tags using ButtonFactory object        
mockMaker.generateFrame();
//Retrieve elements (if needed)
```
In XML
```xml
<Button>Something</Button>
```
