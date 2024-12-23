package cn.trystage.config;

import cn.trystage.Client;
import cn.trystage.module.Module;
import com.google.gson.JsonElement;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Config {
    public String name;
    public Config(String name){
        this.name = name;
    }
    public void loadConfig(File configDir){
        // inject config into modules
        for (Module module : Client.INSTANCE.moduleManager.getModules()){
        }
    }
}
