package mocking.panels;

import mocking.JComponentMockFactory;
import mocking.MockFactory;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.swing.*;

public class ScrollPaneMockFactory extends JComponentMockFactory {
    @Override
    public JComponent buildComponent(MockNode node) {
        return buildComponent(node, null);
    }

    @Override
    public JComponent buildComponent(MockNode node, MockFactory mockMaker) {

        JScrollPane scrollPane = new JScrollPane();
        if(node.hasAttribute("h-policy")) {
            int policy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
            switch (node.getAttribute("h-policy")) {
                case "always": policy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS; break;
                case "never": policy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER; break;
            }
            scrollPane.setHorizontalScrollBarPolicy(policy);
        }

        if(node.hasAttribute("v-policy")) {
            int policy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
            switch (node.getAttribute("v-policy")) {
                case "always": policy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS; break;
                case "never": policy = JScrollPane.VERTICAL_SCROLLBAR_NEVER; break;
            }
            scrollPane.setVerticalScrollBarPolicy(policy);
        }

        if(mockMaker != null) {
            if(node.hasAttribute("id")) {
                mockMaker.register(node.getAttribute("id"), scrollPane);
            }
            addViewport(node.getNode(), scrollPane, mockMaker);
            return scrollPane;
        }
        addViewport(node.getNode(), scrollPane);
        return scrollPane;
    }

    private void addViewport(Node e, JScrollPane scrollPane) {
        for(int i = 0; i < e.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(e.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                JComponent component = mockNode.getComponent(null);
                scrollPane.setViewportView(component);
                break;
            }

        }
    }

    private void addViewport(Node e, JScrollPane scrollPane, MockFactory mockMaker) {
        for(int i = 0; i < e.getChildNodes().getLength() ; i++) {
            MockNode mockNode = new MockNode(e.getChildNodes().item(i));
            if(!mockNode.shouldIgnore()) {
                JComponent component = mockNode.getComponent(mockMaker);
                scrollPane.setViewportView(component);
                break;
            }

        }
    }
}
