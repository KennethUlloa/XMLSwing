package xmlswing.components.windows;

import org.w3c.dom.Node;
import types.TypeContainer;
import types.TypeNode;
import types.TypeNodeFactory;
import xmlswing.ComponentRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class FrameFactory implements TypeNodeFactory<Component> {
    @Override
    public TypeNode<Component> buildNode(Node node, TypeContainer<Component> container) {
        TypeNode<Component> typeNode = new TypeNode<Component>(node) {
            @Override
            public Component parseObject() {
                JFrame frame = new JFrame();

                int width = frame.getWidth();
                int height = frame.getHeight();
                if (hasAttribute("width")) {
                    width = Integer.parseInt(getAttribute("width"));
                }
                if (hasAttribute("height")) {
                    height = Integer.parseInt(getAttribute("height"));
                }
                if (hasAttribute("title")) {
                    frame.setTitle(getAttribute("title"));
                }
                frame.setSize(width, height);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                switch (getAttribute("onclose")) {
                    case "dispose":
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        break;
                    case "hide":
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        break;
                    case "nothing":
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        break;
                    default:
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }

                if (hasAttribute("icon")) {
                    String path = getAttribute("icon");
                    BufferedImage image = null;
                    try {
                        if (path.startsWith("res:")) {
                            image = ImageIO.read(Objects.requireNonNull(
                                    FrameFactory.class.getResourceAsStream(path.replace("res:", ""))));
                        } else {
                            image = ImageIO.read(new File(path));
                        }
                        if (image != null) {
                            frame.setIconImage(image);
                        }
                    } catch (Exception ignored) {
                    }
                }

                for (int i = 0; i < getNode().getChildNodes().getLength(); i++) {
                    Node child = getNode().getChildNodes().item(i);
                    if (container.getFactory(child.getNodeName()) != null) {
                        Component component = container.getFactory(child.getNodeName()).buildNode(child, container).getObject();
                        frame.setContentPane((Container) component);
                    }
                }
                return frame;
            }

        };
        ComponentRepository.registerNode(typeNode, container);
        return typeNode;
    }
}
