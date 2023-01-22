# XMLSwing
XML based Swing UI elements for Java.\
The goal is to separate GUI and business logic as much as it is possible, reduce the time needed to create a basic interface without the need of "drag and drop" tools for GUI implementation
## List of components
Components are listed as it follows *XML tag (Swing Component)*
- Frame (JFrame)
- Button (JButton)
- CheckBox (JCheckBox)
- RadioButton (JRadioButton)
- Slider (JSlider)
- TextArea (JTextArea)
- TextField (JTextField)
- Label (JLabel)
- Border (JPanel with BorderLayout)
- Flow (JPanel with FlowLayout)
- GridBag (JPanel with GridBagLayout)
- Grid (JPanel with GridLayout)
- ScrollPane (JScrollPane)
## Capabilities
XMLSwing allows you to:
- Retrieve elements by id (only for those elements with this attribute)
- Specify common and specific attributes directly in XML (width, height, background, etc...)
- Define and register your own tags for custom elements (create a customized factory is required in order to use this feature)
- Parse simple variables in your XML following the next format: {#variable_name#}
