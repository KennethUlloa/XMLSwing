package xmlswing;

import xmlswing.repositories.ObjectRepository;
import xmlswing.utils.VariableProcessor;

import java.awt.*;
import java.io.InputStream;

/**
 * <h3>Usage</h3>
 * 1. Instantiation<br/>
 * XMLSwing main = new XMLSwing(...);<br/>
 * 2. Building<br/>
 * Object element = main.getRootElement();<br/>
 * 3. Defining actions<br/>
 * Object item = main.getRepository().obtain("id");<br/>
 * item.addActionListener(...);
 */

public class XMLSwing<T extends Component> {

    private InputStream stream;
    private T rootComponent;
    private VariableProcessor variableProcessor;
    private ObjectRepository objects;

    public XMLSwing(InputStream inputStream) {
        this.stream = inputStream;
        variableProcessor = new VariableProcessor(stream);
    }

    public void registerElement(String key, Object c) {
        objects.register(key, c);
    }

    public Object getElement(String key) {
        return objects.obtain(key);
    }

    public <K> K getElement(String key, Class<K> c) {
        return objects.obtain(key, c);
    }

    /*

    public VariableProcessor getVariableProcessor() {
        return variableProcessor;
    }

    private Component build() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(variableProcessor.process());
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        return (Component) factoryRepository.obtain(root.getNodeName()).buildNode(root, this).getObject();
    }
    public Component getRootComponent() throws ParserConfigurationException, IOException, SAXException {
        if(rootComponent == null) {
            rootComponent = build();
        }
        return rootComponent;
    }


    public TypeRepository<Component> getRepository() {
        return repository;
    }

    public TypeNodeFactory<Component, XMLSwing> getFactory(String name) {
        return factoryRepository.obtain(name);
    }

    public <K> K getRootComponent(Class<K> clazz) throws ParserConfigurationException, IOException, SAXException {
        return clazz.cast(getRootComponent());
    }

    public void registerNode(TypeNode<Component> node) {
        if(node != null ) {
            if(node.hasAttribute("id")) {
                getRepository().register(node.getAttribute("id"), node.getObject());
            }
        }
    }*/

}
