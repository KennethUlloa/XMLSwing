package mocking;

import mocking.inputs.*;
import mocking.labels.LabelMockFactory;
import mocking.panels.*;

import java.util.HashMap;

public class MockElementFactory {
    private final HashMap<String, JComponentMockFactory> components;
    private static MockElementFactory instance;
    private MockElementFactory() {
        components = new HashMap<>();
        components.put("TextField", new TextFieldMockFactory());
        components.put("Label", new LabelMockFactory());
        components.put("GridBag", new GridBagFactory());
        components.put("Flow", new FlowPaneFactory());
        components.put("Button", new ButtonMockFactory());
        components.put("Grid", new GridPaneFactory());
        components.put("Border", new BorderPaneMockFactory());
        components.put("ScrollPane", new ScrollPaneMockFactory());
        components.put("TextArea", new TextAreaMockFactory());
        components.put("RadioButton", new RadioButtonFactory());
        components.put("CheckBox", new CheckBoxFactory());
        components.put("Slider", new SliderFactory());
        components.put("Table", new TableFactory());
        components.put("ComboBox", new ComboBoxFactory());
    }

    public static MockElementFactory getInstance() {
        if(instance == null) {
            instance = new MockElementFactory();
        }
        return instance;
    }

    public JComponentMockFactory getFactory(String key) {
        return components.get(key);
    }

}
