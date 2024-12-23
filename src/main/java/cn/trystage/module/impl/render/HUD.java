package cn.trystage.module.impl.render;

import cn.trystage.Client;
import cn.trystage.annotations.event.EventTarget;
import cn.trystage.event.impl.render.EventRender2D;
import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.module.ModuleManager;
import cn.trystage.ui.font.FontManager;
import cn.trystage.utils.RoundedUtil;
import cn.trystage.value.impl.BooleanValue;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;


public class HUD extends Module {
    private final BooleanValue fps = new BooleanValue("Show FPS in title",true);
    public HUD(){
        super("HUD", Category.RENDER);
        this.setState(true);
    }

    @EventTarget
    public void onRender2D(EventRender2D event){
//        String prefix = (Client.INSTANCE.user == "jiuxian_baka") || (Client.INSTANCE.user == "Trystage4C01") ? "[Dev]" : "[Zako]";
        String title;
        if(fps.getValue().booleanValue()){
            title = " " + Client.INSTANCE.version + " | " + Minecraft.getDebugFPS() + "FPS | " + Client.INSTANCE.user;
        }else {
            title = " " + Client.INSTANCE.version + " | " + Client.INSTANCE.user;
        }
        RoundedUtil.drawRound(5, 5, FontManager.bold20.getWidth(Client.INSTANCE.name) + FontManager.font20.getWidth(title) + 8, FontManager.font20.getHeight() + 8, 2, new Color(0, 0, 0, 75));
        FontManager.font20.drawString(title, 8 + FontManager.bold20.getWidth(Client.INSTANCE.name), 8, -1);
        FontManager.bold20.drawString(Client.INSTANCE.name, 7, 10, -14376485);
        FontManager.bold20.drawString(Client.INSTANCE.name, 8, 9, -1);

        int y = 27;
        Collections.sort(ModuleManager.modules, new Comparator<Module>() {
            @Override
            public int compare(Module m1, Module m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
        ModuleManager.modules.sort((o1, o2) -> FontManager.font20.getWidth(o2.getName()) - FontManager.font20.getWidth(o1.getName()));
        for (Module module : ModuleManager.modules) {
            if(!module.state){
                continue;
            }
            String cat = "e";

            switch (module.category){
                case COMBAT:
                    cat = "a";
                    break;
                case MOVEMENT:
                    cat = "b";
                    break;
                case PLAYER:
                    cat = "c";
                    break;
                case OTHER:
                    cat = "e";
                    break;
                case RENDER:
                    cat = "g";
                    break;

            }
            RoundedUtil.drawRoundOutline(5, y, FontManager.icon20.getWidth(cat) + 6, FontManager.font20.getHeight() + 6, 4, 0, new Color(0, 0, 0, 75) , new Color(0,0,0,85));
            FontManager.icon20.drawString(cat, 7, y + 4, -1);
            RoundedUtil.drawRoundOutline(FontManager.icon20.getWidth(cat) + 14, y, FontManager.font20.getWidth(module.name) + 7, FontManager.font20.getHeight() + 6, 4, 0, new Color(0, 0, 0, 75) , new Color(0,0,0,85));
            FontManager.font20.drawString(module.name, FontManager.icon20.getWidth(cat) + 17, y + 2, -1);
            y += 18;
        }
    }

}
