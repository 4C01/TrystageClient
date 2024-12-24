package cn.trystage.module.impl.render;

import cn.trystage.Client;
import cn.trystage.annotations.event.EventTarget;
import cn.trystage.event.impl.render.EventRender2D;
import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.ui.font.FontManager;
import cn.trystage.utils.RenderUtil;
import cn.trystage.utils.RoundedUtil;
import cn.trystage.value.impl.BooleanValue;
import cn.trystage.value.impl.ModeValue;
import cn.trystage.value.impl.NumberValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

public class StatusDisplay extends Module {
    public NumberValue x = new NumberValue("x", 210, 0, 1000, 2);
    public NumberValue y = new NumberValue("y", 28, 0, 500, 2);
    public BooleanValue title = new BooleanValue("title",false);
    public ModeValue type = new ModeValue("Display type",new String[]{"text","highlight"},"highlight");
    public BooleanValue displayName = new BooleanValue("display names",false);
    public BooleanValue fps = new BooleanValue("FPS",true);
    public BooleanValue ping = new BooleanValue("ping",true);
    public BooleanValue tps = new BooleanValue("TPS",true);
    public BooleanValue memory = new BooleanValue("Memory usage",true);
    public ModeValue memoryType = new ModeValue("Memory display type",new String[]{"GB","MB","percentage"},"percentage");
    public NumberValue intervals = new NumberValue("Display intervals", 18, 15, 500,1);
    public StatusDisplay() {
        super("StatusDisplay", Category.RENDER);
        this.setState(true);
    }



    @EventTarget
    public void onRender2D(EventRender2D event){
        int posy = y.getValue().intValue();
        // 获取当前客户端玩家
        if(title.getValue()){
            if(type.getValue() == "highlight") {
                RoundedUtil.drawRound(x.getValue().intValue(), posy, FontManager.tenacitybold22.getWidth("Game Status") + 6, FontManager.tenacitybold22.getHeight() + 6, 3, new Color(0, 0, 0, 75));
            }
            FontManager.tenacitybold22.drawString("Game Status", x.getValue().intValue() + 2, y.getValue().intValue() + 3, -1);
            posy += FontManager.tenacitybold22.getHeight() + intervals.getValue().intValue() - 8;
        }
        String display;
        if(fps.getValue()) {
            display = (displayName.getValue()? "FPS:" : "") + Minecraft.getDebugFPS() + "fps";
            if(type.getValue() == "highlight"){
                RoundedUtil.drawRoundOutline(x.getValue().intValue(),posy,FontManager.font20.getWidth(display) + 7,FontManager.font20.getHeight() + 6,4,0,new Color(0, 0, 0, 75) , new Color(0,0,0,85));
            }
            FontManager.font20.drawString(display, x.getValue().intValue() + 3,posy + 2, -1);
            posy += intervals.getValue();
        }
        if(ping.getValue()) {
            display = (displayName.getValue()? "ping:" : "") + Client.INSTANCE.mc.getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + "ms";
            if(type.getValue() == "highlight"){
                RoundedUtil.drawRoundOutline(x.getValue().intValue(),posy,FontManager.font20.getWidth(display) + 7,FontManager.font20.getHeight() + 6,4,0,new Color(0, 0, 0, 75) , new Color(0,0,0,85));
            }
            FontManager.font20.drawString(display, x.getValue().intValue() + 3,posy + 2, -1);
            posy += intervals.getValue();
        }
        if(tps.getValue()) {
            display = (displayName.getValue()? "TPS:" : "") + Client.INSTANCE.mcs.getTickCounter() + "tick";
            if(type.getValue() == "highlight"){
                RoundedUtil.drawRoundOutline(x.getValue().intValue(),posy,FontManager.font20.getWidth(display) + 7,FontManager.font20.getHeight() + 6,4,0,new Color(0, 0, 0, 75) , new Color(0,0,0,85));
            }
            FontManager.font20.drawString(display, x.getValue().intValue() + 3,posy + 2, -1);
            posy += intervals.getValue();
        }
        if(memory.getValue()) {
            long usedMemory = Client.INSTANCE.runtime.totalMemory() - Client.INSTANCE.runtime.freeMemory();
            long maxMemory = Client.INSTANCE.runtime.maxMemory();
            display = (displayName.getValue() ? "Memory:" : "") + usedMemory + "/" + maxMemory;
            if(memoryType.is("MB")) {
                display = (displayName.getValue() ? "Memory:" : "") + (usedMemory / (1024 * 1024)) + "MB /" + (maxMemory / (1024 * 1024)) + "MB";
            }
            if(memoryType.is("GB")){
                display = (displayName.getValue() ? "Memory:" : "") + (usedMemory / (1024 * 1024 * 1024)) + "GB /" + (maxMemory / (1024 * 1024 * 1024)) + "GB";
            }
            if(memoryType.is("percentage")){
                display = (displayName.getValue() ? "Memory:" : "") + String.format("%.2f", ((double) usedMemory / maxMemory * 100)) + "%";
            }
            if(type.getValue() == "highlight"){
                RoundedUtil.drawRoundOutline(x.getValue().intValue(),posy,FontManager.font20.getWidth(display) + 7,FontManager.font20.getHeight() + 6,4,0,new Color(0, 0, 0, 75) , new Color(0,0,0,85));
            }
            FontManager.font20.drawString(display, x.getValue().intValue() + 3,posy + 2, -1);
            posy += intervals.getValue();
        }
    }
}
