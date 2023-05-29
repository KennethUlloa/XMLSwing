package xmlswing.components.panels.grid;

import xmlswing.XMLSwing;
import xmlswing.components.AbstractNodeFactory;
import org.w3c.dom.Node;
import xmlswing.types.TypeNode;

import java.awt.*;

/**
 * <h3>Properties</h3>
 * <ul>
 *     <li>row-weights: values separated by space e: ("0 0 1 0 1")</li>
 *     <li>column-weights: values separated by space e: ("0 0 1 0 1")</li>
 *     <li>rows: values separated by space e: ("0 0 1 0 1")</li>
 *     <li>columns: values separated by space e: ("0 0 1 0 1")</li>
 * </ul>
 * A child node may define any or all of the following attributes in order to define its own position
 * <ul>
 *     <li>col: X axis position</li>
 *     <li>row: Y axis position</li>
 *     <li>fill: horizontal, vertical of both</li>
 *     <li>col-span: columns to be taken</li>
 *     <li>row-span: rows to be taken</li>
 *     <li>
 *         anchor: position in its cell
 *         <ul>
 *             <li>top</li>
 *             <li>top-left</li>
 *             <li>top-right</li>
 *             <li>bottom</li>
 *             <li>bottom-left</li>
 *             <li>bottom-right</li>
 *             <li>left</li>
 *             <li>right</li>
 *             <li>center</li>
 *         </ul>
 *     </li>
 *     <li>insets: interior space, e: ("0 10 34 6)</li>
 * </ul>
 *
 */
public class GridBagNodeFactory extends AbstractNodeFactory {

    @Override
    public String getTagName() {
        return "GridBag";
    }

    @Override
    public TypeNode<Component, XMLSwing<?>> nodeBuild(Node node, XMLSwing<?> container) {
        return new GridBagNode(node, container);
    }
}
