package cn.trystage;

import cn.trystage.command.CommandManager;
import cn.trystage.event.EventManager;
import cn.trystage.module.ModuleManager;
import com.yumegod.obfuscation.Native;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;

import static net.minecraft.util.Session.Type.LEGACY;
import static net.minecraft.util.Session.Type.MOJANG;

public enum Client {

    INSTANCE;
    public String name = "TrystageClient";
    public String version = "b1";
    public String user = "";
    public Minecraft mc = Minecraft.getMinecraft();
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public final discordRP discordInstance = new discordRP();
    private String prefix = "";


    public void start() {
        discordInstance.start();
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        user = mc.getSession().getUsername();
        moduleManager.loadMods();
        commandManager.loadCommands();
        if( (user.equals("jiuxian_baka") && mc.getSession().getProfile().getId().toString().equals("72397791-ab16-43a0-a6ec-271a626834c6")) || (user.equals("Trystage4C01") && mc.getSession().getProfile().getId().toString().equals("7ebbfd89-4095-4609-82c6-21b964469928"))){
            prefix = "[DEV]";
        }
        Display.setTitle(name + " " + version + " " + prefix + user);
        System.out.println(mc.getSession().getProfile().getId());
    }
    public void stop() {
        discordInstance.shutdown();
    }

    public discordRP getDiscordInstance(){
        return discordInstance;
    }
}