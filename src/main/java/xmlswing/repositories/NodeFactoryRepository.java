package xmlswing.repositories;

import xmlswing.XMLSwing;
import xmlswing.components.frames.DialogNodeFactory;
import xmlswing.components.input.button.ButtonNodeFactory;
import xmlswing.components.input.formatted.FormattedNodeFactory;
import xmlswing.components.input.groups.MultipleGroupFactory;
import xmlswing.components.input.groups.MultipleGroupNode;
import xmlswing.components.input.groups.SingleGroupFactory;
import xmlswing.components.input.selection.CheckBoxNodeFactory;
import xmlswing.components.input.selection.ComboBoxNodeFactory;
import xmlswing.components.input.selection.RadioButtonNodeFactory;
import xmlswing.components.input.selection.SliderNodeFactory;
import xmlswing.components.input.table.TableNodeFactory;
import xmlswing.components.input.text.TextAreaNodeFactory;
import xmlswing.components.input.text.TextFieldNodeFactory;
import xmlswing.components.menu.item.MenuItemNodeFactory;
import xmlswing.components.panels.tabpane.TabbedPaneFactory;
import xmlswing.types.TypeNodeFactory;
import xmlswing.types.TypeRepository;
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
    private static NodeFactoryRepository instance;
    private NodeFactoryRepository() {
        internalRegister(new FlowNodeFactory());
        internalRegister(new ButtonNodeFactory());
        internalRegister(new FrameFactory());
        internalRegister(new TextFieldNodeFactory());
        internalRegister(new TextAreaNodeFactory());
        internalRegister(new GridBagNodeFactory());
        internalRegister(new CheckBoxNodeFactory());
        internalRegister(new RadioButtonNodeFactory());
        internalRegister(new ComboBoxNodeFactory());
        internalRegister(new ScrollPaneNodeFactory());
        internalRegister(new TableNodeFactory());
        internalRegister(new LabelNodeFactory());
        internalRegister(new SliderNodeFactory());
        internalRegister(new BorderNodeFactory());
        internalRegister(new GridNodeFactory());
        internalRegister(new MenuBarNodeFactory());
        internalRegister(new MenuNodeFactory());
        internalRegister(new MenuItemNodeFactory());
        internalRegister(new TabbedPaneFactory());
        internalRegister(new FormattedNodeFactory());
        internalRegister(new SingleGroupFactory());
        internalRegister(new MultipleGroupFactory());
        internalRegister(new DialogNodeFactory());
    }

    public static void register(TypeNodeFactory<Component, XMLSwing<?>> factory) {
        getInstance().internalRegister(factory);
    }
    
    private static NodeFactoryRepository getInstance() {
        if(instance == null) {
            instance = new NodeFactoryRepository();
        }
        return instance;
    }

    private void internalRegister(TypeNodeFactory<Component, XMLSwing<?>> factory) {
        this.register(factory.getTagName(), factory);
    }

    public static TypeNodeFactory<Component, XMLSwing<?>> get(String key) {
        return getInstance().obtain(key);
    }

}
