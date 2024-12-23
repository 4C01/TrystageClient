package cn.trystage.ui.clickgui.values;

import cn.trystage.value.AbstractValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextValue extends AbstractValue<String> {
    public static TextValue self;
    public String selectedString;
    public static String DEFAULT_STRING;

    public TextValue(String name, String description, String value) {
        super(name);
        selectedString = "";
        this.value = value;
    }

    public TextValue(String name, String value) {
        super(name);
        selectedString = "";
        this.value = value;
    }

    public static TextValue create(String name) {
        return (self = new TextValue(name, DEFAULT_STRING, DEFAULT_STRING));
    }

    public TextValue withDescription(String description) {
//        self.(description);
        return self;
    }

    public TextValue defaultTo(String value) {
        DEFAULT_STRING = (value);
        return self;
    }

}
