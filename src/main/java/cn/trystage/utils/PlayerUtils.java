package cn.trystage.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class PlayerUtils {
    public static void tellPlayer(String text) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(
                ChatFormatting.WHITE + "[" + ChatFormatting.AQUA + "TrystageClient" + ChatFormatting.WHITE + "] " + text));
    }
}