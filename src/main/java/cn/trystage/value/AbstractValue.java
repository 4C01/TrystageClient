package cn.trystage.value;

import cn.trystage.module.Module;
import cn.trystage.utils.vector.Vector2d;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public abstract class AbstractValue<T> {
    public String name;
    public T value;
    protected ArrayList<BooleanSupplier> hideSuppliers;

    protected AbstractValue(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSupplier(BooleanSupplier hide) {
        this.hideSuppliers.add(hide);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isHidden() {
        for(BooleanSupplier supplier : hideSuppliers){
            if(!supplier.getAsBoolean())
                return false;
        }
        return true;
    }

}
