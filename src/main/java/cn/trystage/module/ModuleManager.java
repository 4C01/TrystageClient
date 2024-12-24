package cn.trystage.module;

import cn.trystage.Client;
import cn.trystage.annotations.event.EventTarget;
import cn.trystage.event.impl.misc.EventKeyboard;
import cn.trystage.module.impl.movement.ToggleSprint;
import cn.trystage.module.impl.render.*;
import cn.trystage.value.AbstractValue;
import cn.trystage.value.impl.BooleanValue;
import cn.trystage.value.impl.ModeValue;
import cn.trystage.value.impl.NumberValue;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ModuleManager {
    public static ArrayList<Module> modules;

    public ModuleManager() {
        this.modules = new ArrayList();
        Client.INSTANCE.eventManager.register(this);
    }

    public void loadMods() {
        addModule(new HUD());
        addModule(new PotionEffects());
        addModule(new ToggleSprint());
        addModule(new Clickgui());
        addModule(new FullBright());
        addModule(new CustomCrossair());
        addModule(new StatusDisplay());
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name))
                return m;
        }
        return null;
    }
    private void addModule(Module m) {
        for (final Field field : m.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                final Object obj = field.get(m);
                if (obj instanceof AbstractValue<?>){
                    m.getValues().add((AbstractValue<?>) obj);
                    sortValue(m);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        modules.add(m);
    }

    @EventTarget
    public void onKey(EventKeyboard eventKeyBoard) {
        for (Module mod : modules) {
            if (mod.getKeyCode() == eventKeyBoard.getKey())
                mod.toggle();
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    private void sortValue(Module mod) {
        ArrayList<AbstractValue<?>> sorted = new ArrayList<>();
        ArrayList<AbstractValue<?>> values = mod.getValues();

        for (AbstractValue<?> v : values) {
            if (v instanceof ModeValue)
                sorted.add(v);
        }
        for (AbstractValue<?> v : values) {
            if (v instanceof NumberValue)
                sorted.add(v);
        }

        for (AbstractValue<?> v : values) {
            if (v instanceof BooleanValue)
                sorted.add(v);
        }

        mod.setValues(sorted);
    }
//    public static List<Module> modules;
//
//    public ModuleManager(){
//        modules = new ArrayList<>();
//        addModules(new HUD());
//        addModules(new ToggleSprint());
//        addModules(new Clickgui());
//    }
//    public void addModules(Module module){
//        modules.add(module);
//    }
//
//    @EventTarget
//    public void onKey(EventKeyboard keyboard){
//        for (Module module : ModuleManager.modules) {
//            if(module.key == keyboard.getKey()){
//                module.setState(true);
//            }
//        }
//    }
}
