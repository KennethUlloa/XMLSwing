package html;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class HTMLView extends JFrame {
    public HTMLView(String index) throws IOException {
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        HTMLDocument htmlDocument = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        URL baseURL = getClass().getClassLoader().getResource(index);
        htmlDocument.setBase(baseURL);
        JEditorPane editor = new JEditorPane();
        editor.setEditable(false);
        editor.setEditorKit(htmlEditorKit);
        //editor.read(getClass().getClassLoader().getResourceAsStream(index), htmlDocument);
        editor.read(new FileInputStream(index),
                htmlDocument);

        setContentPane(new JScrollPane(editor));
    }
}
