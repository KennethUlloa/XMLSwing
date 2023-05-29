package xmlswing.components.panels.tabpane;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * tabName: determines whether a node should be included in the tab or not.
 */
public class TabbedPaneFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "TabPane";
    }

    @Override
    public TypeNode<Component, XMLSwing<?>> nodeBuild(Node node, XMLSwing<?> context) {
        return new TabPaneNode(node, context);
    }
}