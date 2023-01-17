package mocking;

import mocking.inputs.ButtonMockFactory;
import mocking.labels.LabelMockFactory;
import mocking.panels.FlowPaneFactory;
import mocking.panels.GridBagMockFactory;
import mocking.inputs.InputMockFactory;
import mocking.panels.GridPaneFactory;

import java.util.HashMap;

public class MockElementFactory {
    private HashMap<String, JComponentMockFactory> components;
    private static MockElementFactory instance;
    private MockElementFactory() {
        components = new HashMap<>();
        components.put("input", new InputMockFactory());
        components.put("label", new LabelMockFactory());
        components.put("gridbag", new GridBagMockFactory());
        components.put("flow", new FlowPaneFactory());
        components.put("button", new ButtonMockFactory());
        components.put("grid", new GridPaneFactory());
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
