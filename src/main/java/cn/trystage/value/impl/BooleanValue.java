package cn.trystage.value.impl;

import cn.trystage.value.AbstractValue;

public class BooleanValue extends AbstractValue<Boolean> {

    public BooleanValue(String name, boolean enabled) {
        super(name);
        this.setValue(enabled);
    }

}
