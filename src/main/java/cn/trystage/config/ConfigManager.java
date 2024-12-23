package cn.trystage.config;

import cn.trystage.Client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class ConfigManager {
    public static final File dir = new File(Client.INSTANCE.mc.mcDataDir, Client.INSTANCE.name + "/configs");
    public static final File sessionConfigFile = new File(Client.INSTANCE.mc.mcDataDir, Client.INSTANCE.name + "/configs/session.json");
    public ArrayList<String> configName;
    public Config sessionConfig;

    public ConfigManager() {
        this.configName = new ArrayList<String>();
    }
    public void loadConfigs(){
        if (!dir.exists()) {
            dir.mkdir();
        }
        if(!sessionConfigFile.exists()){
            try {
                sessionConfigFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for(File config : dir.listFiles()) {
            configName.add(config.getName());
        }
        sessionConfig.loadConfig(sessionConfigFile);
    }

}
