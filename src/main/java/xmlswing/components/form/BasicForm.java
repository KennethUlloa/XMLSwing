package xmlswing.components.form;

import java.util.Map;

public class BasicForm extends Form<Map<String, Object>> {
    @Override
    public Map<String, Object> processValue() throws EmptyValueNotAllowedException {
        return getValues();
    }
}
