import org.xml.sax.SAXException;
import xmlswing.XMLSwing;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        XMLSwing swing = new XMLSwing(Files.newInputStream(Paths.get("./demo.xml")));
        JFrame frame = (JFrame) swing.getRootComponent();
        //JMenuItem item = (JMenuItem) swing.getRepository().obtain("guardarOp");
        XMLSwing panel1 = new XMLSwing(Files.newInputStream(Paths.get("./panel1.xml")));
        XMLSwing panel2 = new XMLSwing(Files.newInputStream(Paths.get("./panel2.xml")));
        JPanel panelA = (JPanel) panel1.getRootComponent();
        JPanel panelB = (JPanel) panel2.getRootComponent();
        JPanel mainPanel = (JPanel) swing.getRepository().obtain("mainPanel");

        swing.getRepository().obtain("anterior", JButton.class).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.remove(panelA);
                mainPanel.add(panelB, BorderLayout.CENTER);
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });

        ((JButton)swing.getRepository().obtain("siguiente")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.remove(panelB);
                mainPanel.add(panelA, BorderLayout.CENTER);
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });

        frame.setVisible(true);





    }
}
