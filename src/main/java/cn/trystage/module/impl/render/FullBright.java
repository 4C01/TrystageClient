package cn.trystage.module.impl.render;

import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.value.AbstractValue;
import cn.trystage.value.impl.NumberValue;
import com.yumegod.obfuscation.Native;

import java.util.ArrayList;


public class FullBright extends Module {
    private final NumberValue gamma = new NumberValue("Gamma",10F,0F,10F,0.1F);
    public FullBright() {
        super("FullBright", Category.RENDER);
        this.setState(true);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.gameSettings.gammaSetting = gamma.getValue().intValue();
    }

    @Override
    public void onSetValues() {
        super.onSetValues();
        mc.gameSettings.gammaSetting = gamma.getValue().intValue();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.gammaSetting = 1;
    }
}
