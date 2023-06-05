package xmlswing.components.form;

import java.awt.*;

public abstract class FormEntry<T>  {
    public final String name;
    public final T element;
    public final boolean required;

    public FormEntry(String name, T value, boolean required) {
        this.name = name;
        this.element= value;
        this.required = required;
    }

    public Object getValue() throws EmptyValueNotAllowedException {
        if(required && isEmpty()) {
            throw new EmptyValueNotAllowedException(name + " doesn't accept empty values");
        }
        return getRawValue();
    }

    public boolean isEmpty() {
        Object val = getRawValue();
        if (val == null) return true;
        if (val instanceof String) {
            return ((String) val).isEmpty();
        }
        return false;
    }

    public abstract void clearValue();

    public abstract Object getRawValue();

}
