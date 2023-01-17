package mocking.frames;

import mocking.MockMaker;
import mocking.MockNode;
import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FrameMock {
    private MockNode mockNode;
    public FrameMock(Node node) {
        this.mockNode = new MockNode(node);
    }
    public JFrame generate() {
        JFrame frame = new JFrame();
        setSize(frame);
        setTitle(frame);
        setOnClose(frame);
        setResizable(frame);
        setIcon(frame);
        return frame;
    }

    private void setIcon(JFrame frame) {
        if(!mockNode.getAttributes().containsKey("icon")) return;
        try {
            BufferedImage image = ImageIO.read(new File(mockNode.getAttributes().get("icon")));
            frame.setIconImage(image);
        } catch (IOException ignored) {}
    }

    private void setResizable(JFrame frame) {
        HashMap<String, String> attr = mockNode.getAttributes();
        if(!attr.containsKey("resize")) return;
        frame.setResizable(attr.get("resize").equals("false"));

    }
    private void setOnClose(JFrame frame) {
        HashMap<String, String> attr = mockNode.getAttributes();
        if(!attr.containsKey("onclose")) return;
        switch (attr.get("onclose")) {
            case "exit": frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); return;
            case "dispose": frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); return;
            case "": frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); return;
            case "hide": frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
    }

    private void setTitle(JFrame frame) {
        HashMap<String, String> attr = mockNode.getAttributes();
        if(!attr.containsKey("title")) return;
        frame.setTitle(attr.get("title"));
    }

    private void setSize(JFrame frame) {
        int width = 400, height = 400;
        if(mockNode.getAttributes().containsKey("width")) {
            try {
                width = Integer.parseInt(mockNode.getAttributes().get("width"));
            } catch (NumberFormatException ignored) {}
        }
        if(mockNode.getAttributes().containsKey("height")) {
            try {
                height = Integer.parseInt(mockNode.getAttributes().get("height"));
            } catch (NumberFormatException ignored) {}
        }
        frame.setSize(width, height);
    }

}
