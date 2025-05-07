package cn.trystage;

import cn.trystage.command.CommandManager;
import cn.trystage.event.EventManager;
import cn.trystage.module.ModuleManager;
import cn.trystage.ui.SplashProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Session;
import org.lwjgl.opengl.Display;

public enum Client {

    INSTANCE;
    public String name = "TrystageClient";
    public String version = "b1";
    public String user = "";
    public Minecraft mc = Minecraft.getMinecraft();
    public MinecraftServer mcs = MinecraftServer.getServer();
    public Runtime runtime;
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public final discordRP discordInstance = new discordRP();
    private String prefix = "";


    public void start() {
        System.setProperty("net.java.games.input.Logger", "false");
        SplashProgress.setPrgress(2,"Trystage - Initializing");
        runtime = Runtime.getRuntime();
        discordInstance.start();
        SplashProgress.setPrgress(3,"Trystage - Link to Discord");
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        SplashProgress.setPrgress(4,"Trystage - Register Modules");
        user = mc.getSession().getUsername();
        moduleManager.loadMods();
        commandManager.loadCommands();
        SplashProgress.setPrgress(5,"Trystage - Small Feature");
        if((user.equals("jiuxian_baka") && mc.getSession().getSessionType() == Session.Type.MSA) || (user.equals("Trystage4C01")) || (user.equals("TrystageBedwars") && mc.getSession().getSessionType() == Session.Type.MSA)){
            prefix = "[DEV]";
        }
        Display.setTitle(name + " " + version + " " + prefix + user);

        SplashProgress.setPrgress(6,"Trystage - Almost Done......");
    }
    public void stop() {
        discordInstance.shutdown();
    }

    public discordRP getDiscordInstance(){
        return discordInstance;
    }
}