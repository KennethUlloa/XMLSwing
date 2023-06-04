package xmlswing.components.form;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public abstract class Form<T> {
    private List<FormEntry<? extends Component>> elements;

    public Form() {
        elements = new ArrayList<>();
    }

    public void add(FormEntry<? extends Component> element) {
        if (element != null)
            elements.add(element);

    }

    public Map<String, Object> getValues() throws EmptyValueNotAllowedException {
        Map<String, Object> values = new HashMap<>();
        for(FormEntry<? extends Component> el: elements) {
            values.put(el.name, el.getValue());
        }

        return values;
    }

    public abstract T processValue() throws EmptyValueNotAllowedException;
}
