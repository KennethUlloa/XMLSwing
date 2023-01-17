package mocking;

import mocking.inputs.ButtonMockFactory;
import mocking.labels.LabelMockFactory;
import mocking.panels.BorderPaneMockFactory;
import mocking.panels.FlowPaneFactory;
import mocking.panels.GridBagMockFactory;
import mocking.inputs.TextFieldMockFactory;
import mocking.panels.GridPaneFactory;

import java.util.HashMap;

public class MockElementFactory {
    private final HashMap<String, JComponentMockFactory> components;
    private static MockElementFactory instance;
    private MockElementFactory() {
        components = new HashMap<>();
        components.put("TextField", new TextFieldMockFactory());
        components.put("Label", new LabelMockFactory());
        components.put("GridBag", new GridBagMockFactory());
        components.put("Flow", new FlowPaneFactory());
        components.put("Button", new ButtonMockFactory());
        components.put("Grid", new GridPaneFactory());
        components.put("Border", new BorderPaneMockFactory());
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
