package cn.trystage.utils;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerDataFeatured extends ServerData {

    public static final ResourceLocation FeatureServerIcon = new ResourceLocation("Trystage/images/feature.png");

    public ServerDataFeatured(String ServerName, String ServerIP) {
        super(ServerName, ServerIP, false);
    }
}
