package xmlswing.components.panels;

import org.w3c.dom.Node;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.components.PropertiesReader;
import xmlswing.components.ComponentNode;

import javax.swing.*;
import java.awt.*;

/**
 * <h3>Properties</h3>
 * <ul>
 *     <li>
 *         h-policy
 *         <ul>
 *             <li>always</li>
 *             <li>never</li>
 *         </ul>
 *     </li>
 *     <li>
 *         v-policy
 *         <ul>
 *             <li>always</li>
 *             <li>never</li>
 *         </ul>
 *     </li>
 * </ul>
 */
public class ScrollPaneNodeFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new ComponentNode(node, container) {
            @Override
            public Component parseObject() {
                JScrollPane scrollPane = new JScrollPane();
                if(hasAttribute("h-policy")) {
                    int policy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
                    switch (getAttribute("h-policy")) {
                        case "always": policy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS; break;
                        case "never": policy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER; break;
                    }
                    scrollPane.setHorizontalScrollBarPolicy(policy);
                }

                if(hasAttribute("v-policy")) {
                    int policy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
                    switch (getAttribute("v-policy")) {
                        case "always": policy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS; break;
                        case "never": policy = JScrollPane.VERTICAL_SCROLLBAR_NEVER; break;
                    }
                    scrollPane.setVerticalScrollBarPolicy(policy);
                }
                for(int i = 0; i < getNode().getChildNodes().getLength() ; i++) {
                    TypeNodeFactory<Component> factory = getContainer().getFactory(getNode().getChildNodes().item(i).getNodeName());
                    if(factory != null) {
                        TypeNode<Component> typeComponent = factory.buildNode(getNode().getChildNodes().item(i), getContainer());
                        scrollPane.setViewportView(typeComponent.getObject());
                        break;
                    }
                }
                return scrollPane;
            }
        };
        PropertiesReader.setUpComponent(typeNode);
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }

    @Override
    public String getTagName() {
        return "ScrollPane";
    }
}
