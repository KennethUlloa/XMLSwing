package xmlswing.components.frames;

import org.w3c.dom.Node;
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

public class DialogNode extends AbstractNode<JDialog> {
    public DialogNode(Node node, XMLSwing<?> context) {
        super(node, context);
    }

    @Override
    public JDialog getRootElement() {
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(hasAttribute("modal"));
        int width = 200;
        int height = 200;
        if (hasAttribute("width")) {
            width = Integer.parseInt(getAttribute("width"));
        }
        if (hasAttribute("height")) {
            height = Integer.parseInt(getAttribute("height"));
        }
        if (hasAttribute("title")) {
            dialog.setTitle(getAttribute("title"));
        }
        dialog.setSize(width, height);

        if(hasAttribute("onclose")){
            switch (getAttribute("onclose")) {
                case "dispose": dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);break;
                case "hide": dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);break;
                case "nothing": dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);break;
            }
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
                    dialog.setIconImage(image);
                }
            } catch (Exception ignored) {
            }
        }
        return dialog;
    }

    @Override
    public void parseChildNode(JDialog parent, Node item) {
        TypeNodeFactory<Component, XMLSwing<?>> factory = NodeFactoryRepository.get(item.getNodeName());
        if(factory == null) {
            return;
        }
        TypeNode<Component, XMLSwing<?>> typeNode = factory.buildNode(item, getContext());
        if(typeNode.hasAttribute("is-content-pane")) {
            if (typeNode.getObject() instanceof Container) {
                parent.setContentPane((Container) typeNode.getObject());
                return;
            }
        }
        if(typeNode.getObject() instanceof JMenuBar) {
            parent.setJMenuBar((JMenuBar) typeNode.getObject());
        }
    }
}
