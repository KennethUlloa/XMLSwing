package xmlswing.repositories;

import xmlswing.XMLSwing;
import xmlswing.types.TypeNodeFactory;
import xmlswing.types.TypeRepository;
import xmlswing.components.input.*;
import xmlswing.components.label.LabelNodeFactory;
import xmlswing.components.menu.bar.MenuBarNodeFactory;
import xmlswing.components.menu.MenuNodeFactory;
import xmlswing.components.panels.*;
import xmlswing.components.panels.border.BorderNodeFactory;
import xmlswing.components.panels.flow.FlowNodeFactory;
import xmlswing.components.panels.grid.GridBagNodeFactory;
import xmlswing.components.panels.grid.GridNodeFactory;
import xmlswing.components.frames.FrameFactory;

import java.awt.*;

public class NodeFactoryRepository extends TypeRepository<TypeNodeFactory<Component, XMLSwing<?>>> {
    private static NodeFactoryRepository instance = new NodeFactoryRepository();
    private NodeFactoryRepository() {

        register(new FlowNodeFactory());
        register(new ButtonNodeFactory());
        register(new FrameFactory());
        register(new TextFieldNodeFactory());
        register(new TextAreaFactory());
        register(new GridBagNodeFactory());
        register(new CheckBoxNodeFactory());
        register(new RadioButtonNodeFactory());
        register(new ComboBoxNodeFactory());
        register(new ScrollPaneNodeFactory());
        register(new TableNodeFactory());
        register(new LabelNodeFactory());
        register(new SliderNodeFactory());
        register(new BorderNodeFactory());
        register(new GridNodeFactory());
        register(new MenuBarNodeFactory());
        register(new MenuNodeFactory());
        register(new MenuItemNodeFactory());
        register(new TabbedPaneFactory());
        register(new TabFactory());

    }

    public static void register(TypeNodeFactory<Component, XMLSwing<?>> factory) {
        instance.register(factory.getTagName(), factory);
    }

    public static TypeNodeFactory<Component, XMLSwing<?>> get(String key) {
        return instance.obtain(key);
    }

}
