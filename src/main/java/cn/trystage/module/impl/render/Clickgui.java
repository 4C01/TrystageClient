package cn.trystage.module.impl.render;

import cn.trystage.data;
import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.ui.clickgui.gui.ClickGui;
import cn.trystage.value.impl.ModeValue;
import cn.trystage.value.impl.NumberValue;
import org.lwjgl.input.Keyboard;


public class Clickgui extends Module {
    public ModeValue test = new ModeValue("test", new String[]{"1","2","3","4","5"}, "5");
    public NumberValue Volume = new NumberValue("Volume",1F,0F,1F,0.1);
    public Clickgui() {
        super("Clickgui", Category.RENDER, Keyboard.KEY_RSHIFT);
    }
    @Override
    public void onEnable(){
        mc.displayGuiScreen(new ClickGui());
        this.setState(false);
    }

    @Override
    public void onSetValues() {
        super.onSetValues();
        data.setToggleSoundVolume(Volume.getValue().floatValue());
    }
}
