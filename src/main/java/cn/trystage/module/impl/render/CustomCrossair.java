package cn.trystage.module.impl.render;

import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.value.impl.BooleanValue;
import cn.trystage.value.impl.ModeValue;
import cn.trystage.value.impl.NumberValue;
import com.yumegod.obfuscation.Native;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.List;
@Native
public class CustomCrossair extends Module {
    public BooleanValue dot = new BooleanValue("Dot",true);
    public NumberValue dotSize = new NumberValue("Size",0.2F,0.1F,5F,0.1F);
    public ModeValue customCrossairType = new ModeValue("Type",new String[]{"arrow","circle","cross","dot","heart","triangle","vanilla"},"cross");
    public NumberValue height = new NumberValue("Height",16,0,64,2);
    public NumberValue width = new NumberValue("Height",16,0,64,2);
    public CustomCrossair(){
        super("CustomCrossair", Category.RENDER);
        this.setState(true);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        GuiIngame.customCrossAir = true;
    }

    @Override
    public void onSetValues() {
        super.onSetValues();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.gammaSetting = 1;
        GuiIngame.customCrossAir = false;
    }
}
