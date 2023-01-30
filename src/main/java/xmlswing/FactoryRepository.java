package xmlswing;

import types.TypeNodeFactory;
import types.TypeRepository;
import xmlswing.components.input.*;
import xmlswing.components.label.LabelNodeFactory;
import xmlswing.components.panels.*;
import xmlswing.components.windows.FrameFactory;

import java.awt.*;

public class FactoryRepository extends TypeRepository<TypeNodeFactory<Component>> {
    public FactoryRepository() {
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
    }

    private void register(TypeNodeFactory<Component> factory) {
        register(factory.getTagName(), factory);
    }
}
