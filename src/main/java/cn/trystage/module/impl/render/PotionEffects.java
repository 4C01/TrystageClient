package cn.trystage.module.impl.render;

import cn.trystage.annotations.event.EventTarget;
import cn.trystage.event.impl.render.EventRender2D;
import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.utils.RenderUtil;
import cn.trystage.ui.font.FontManager;
import cn.trystage.utils.RoundedUtil;
import cn.trystage.value.impl.BooleanValue;
import cn.trystage.value.impl.NumberValue;
import com.yumegod.obfuscation.Native;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

@Native
public class PotionEffects extends Module {
    public NumberValue x = new NumberValue("x", 0, 0, 1000, 10);
    public NumberValue y = new NumberValue("y", 300, 0, 1000, 10);
    public BooleanValue title = new BooleanValue("title", true);

    public PotionEffects() {
        super("PotionEffects", Category.RENDER);
        this.setState(true);
    }

    @EventTarget
    public void onRender2D(EventRender2D event){

        // 获取当前客户端玩家
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if(title.getValue()){
        RoundedUtil.drawRound(x.getValue().intValue(), y.getValue().intValue(), FontManager.tenacitybold22.getWidth("Potion Effects") + 8, FontManager.tenacitybold22.getHeight() + 8, 2, new Color(0, 0, 0, 75));
        FontManager.tenacitybold22.drawString("Potion Effects", x.getValue().intValue() + 3, y.getValue().intValue() + 3, -1);
        }
        int posy = y.getValue().intValue() + FontManager.bold20.getHeight() + 13;
        if (player != null) {
            ArrayList<PotionEffect> ActivePotionEffects = new ArrayList<PotionEffect>(player.getActivePotionEffects());
            ActivePotionEffects.sort((o1, o2) -> o2.getDuration() - o1.getDuration());
            for (PotionEffect effect : ActivePotionEffects) {
                String potion = I18n.format(effect.getEffectName()) + " " + intToRomanByGreedy((effect.getAmplifier() + 1) == 1 ? 0 : effect.getAmplifier() + 1) + "  " + (effect.getDuration() / 20) / 60 + ":"+ (effect.getDuration() / 20) % 60;
                RoundedUtil.drawRoundOutline(x.getValue().intValue(), posy, FontManager.font20.getHeight() + 6, FontManager.font20.getHeight() + 6, 4, 0, new Color(0, 0, 0, 75) , new Color(0,0,0,85));
                RenderUtil.drawCustomImage(x.getValue().intValue() + 3, posy + 2,FontManager.font20.getHeight() + 1 , FontManager.font20.getHeight() + 1 , new ResourceLocation("Trystage/images/potions/" + effect.getPotionID() + ".png"));
                RoundedUtil.drawRoundOutline(x.getValue().intValue() + FontManager.font20.getHeight() + 9, posy, FontManager.font20.getWidth(potion) + 8, FontManager.font20.getHeight() + 6, 4, 0, new Color(0, 0, 0, 75) , new Color(0,0,0,85));
                FontManager.font20.drawString(potion, x.getValue().intValue() + 12 + FontManager.font20.getHeight(),  posy + 2, -1);
                posy += 18;
            }
        }
    }

    private String intToRomanByGreedy(int num) {
        if(num == 0)
            return "";
        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < values.length && num >= 0; i++)
            while (values[i] <= num) {
                num -= values[i];
                stringBuilder.append(symbols[i]);
            }

        return stringBuilder.toString();
    }


}
