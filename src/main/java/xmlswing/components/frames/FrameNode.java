package xmlswing.components.frames;

import org.w3c.dom.Node;
import xmlswing.WrapNode;
import xmlswing.XMLSwing;
import xmlswing.components.AbstractNode;
import xmlswing.repositories.NodeFactoryRepository;
import xmlswing.types.TypeNode;
import xmlswing.types.TypeNodeFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class FrameNode extends AbstractNode<JFrame> {
    public FrameNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public void parseChildNode(JFrame parent, Node item) {
        WrapNode node_ = new WrapNode(item);
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory == null) {
            return;
        }
        TypeNode<Component, XMLSwing<?>> typeNode = factory.buildNode(item, getContext());
        if(node_.hasAttribute("is-content-pane")) {
            if (typeNode.getObject() instanceof Container) {
                parent.setContentPane((Container) typeNode.getObject());
                return;
            }
        }
        if(typeNode.getObject() instanceof JMenuBar) {
            parent.setJMenuBar((JMenuBar) typeNode.getObject());
        }
    }

    @Override
    public JFrame getRootElement() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(hasAttribute("lafClass")) {
            try {
                UIManager.setLookAndFeel(getAttribute("lafClass"));
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        }

        int width = 200;
        int height = 200;

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
            case "dispose": frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);break;
            case "hide": frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);break;
            case "nothing": frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);break;
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
        return frame;
    }
}
