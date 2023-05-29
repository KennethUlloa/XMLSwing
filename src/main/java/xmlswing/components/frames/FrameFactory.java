package xmlswing.components.frames;

import org.w3c.dom.Node;
import xmlswing.components.AbstractNodeFactory;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;
import xmlswing.XMLSwing;
import xmlswing.components.ComponentNode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class FrameFactory extends AbstractNodeFactory {


    @Override
    public String getTagName() {
        return "Frame";
    }


    @Override
    public TypeNode<Component, XMLSwing<?>> nodeBuild(Node node, XMLSwing<?> context) {
        return new FrameNode(node, context);
    }
}
