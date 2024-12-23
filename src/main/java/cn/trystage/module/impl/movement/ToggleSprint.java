package cn.trystage.module.impl.movement;

import cn.trystage.annotations.event.EventTarget;
import cn.trystage.event.impl.misc.EventUpdate;
import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.value.impl.BooleanValue;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;


public class ToggleSprint extends Module {
    public BooleanValue test = new BooleanValue("test", false);
    public ToggleSprint(){
        super("ToggleSprint", Category.MOVEMENT, Keyboard.KEY_I);
        this.setState(true);
    }

    Minecraft mc = Minecraft.getMinecraft();

    @EventTarget
    public void onUpdate(EventUpdate event){
        mc.gameSettings.keyBindSprint.pressed = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.keyBindSprint.pressed = false;
    }
}
