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
        register("Flow", new FlowNodeFactory());
        register("Button", new ButtonNodeFactory());
        register("Frame", new FrameFactory());
        register("TextField", new TextFieldNodeFactory());
        register("TextArea", new TextAreaFactory());
        register("GridBag", new GridBagNodeFactory());
        register("CheckBox", new CheckBoxNodeFactory());
        register("RadioButton", new RadioButtonNodeFactory());
        register("ComboBox", new ComboBoxNodeFactory());
        register("ScrollPane", new ScrollPaneNodeFactory());
        register("Table", new TableNodeFactory());
        register("Label", new LabelNodeFactory());
        register("Slider", new SliderNodeFactory());
        register("Border", new BorderNodeFactory());
        register("Grid", new GridNodeFactory());
    }
}
