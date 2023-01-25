package xmlswing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import types.TypeContainer;
import types.TypeNodeFactory;
import types.TypeRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


public class XMLSwing implements TypeContainer<Component> {
    private ComponentRepository repository;
    private FactoryRepository factoryRepository;
    private InputStream stream;
    private Component rootComponent;
    public XMLSwing(InputStream inputStream) {
        this.stream = inputStream;
        repository = new ComponentRepository();
        factoryRepository = new FactoryRepository();
    }
    private Component build() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(stream);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        return factoryRepository.obtain(root.getNodeName()).buildNode(root, this).getObject();
    }
    public Component getRootComponent() throws ParserConfigurationException, IOException, SAXException {
        if(rootComponent == null) {
            rootComponent = build();
        }
        return rootComponent;
    }

    @Override
    public TypeRepository<Component> getRepository() {
        return repository;
    }

    @Override
    public TypeNodeFactory<Component> getFactory(String name) {
        return factoryRepository.obtain(name);
    }
}