package cn.trystage.ui;

import cn.trystage.ui.font.FontManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SplashProgress {
    private static final int MAX = 7;
    private static int PROGRESS = 0;
    private static String CURRENT = "";
    private static ResourceLocation splash = new ResourceLocation("Trystage/images/splash.png");

    public static void update(){
        if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null)
            return;
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void setPrgress(int givenProgress,String givenText){
        PROGRESS = givenProgress;
        CURRENT = givenText;
        update();
    }

    public static void drawSplash(TextureManager textureManager){
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledResolution.getScaleFactor();

        Framebuffer framebuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor,scaledResolution.getScaledHeight() * scaleFactor,true);
        framebuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.00,(double)scaledResolution.getScaledWidth(),(double)scaledResolution.getScaledHeight(),0.00,1000.00,3000.00);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F,0.0F,-2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if(splash == null)
            splash = new ResourceLocation("images/splash.png");
        textureManager.bindTexture(splash);

        GlStateManager.resetColor();
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);

        Gui.drawScaledCustomSizeModalRect(0,0,0,0,1920,1080,scaledResolution.getScaledWidth(),scaledResolution.getScaledHeight(),1920,1080);
        drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor,scaledResolution.getScaledHeight()*scaleFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516,0.1F);

        Minecraft.getMinecraft().updateDisplay();
    }
    private static void drawProgress(){
        if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null)
            return;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        double nProgress = (double) PROGRESS;
        double calc = (nProgress / MAX) * sr.getScaledWidth();
        Gui.drawRect(0,sr.getScaledHeight() - 35,sr.getScaledWidth(),sr.getScaledHeight(),new Color(0,0,0,50).getRGB());

        GlStateManager.resetColor();
        resetTextureState();
        FontManager.font20.drawString(CURRENT,20,sr.getScaledHeight() - 25,0xFFFFFFFF);
        String progPercent = PROGRESS + "/" + MAX;
        FontManager.font20.drawString(progPercent,sr.getScaledWidth() - 20 - FontManager.font20.getWidth(progPercent),sr.getScaledHeight() - 25, 0xe1FFe1FF );

        GlStateManager.resetColor();
        resetTextureState();

        Gui.drawRect(0,sr.getScaledHeight() - 35,sr.getScaledWidth(),sr.getScaledHeight() - 30,new Color(150,200,255,250).getRGB());
        Gui.drawRect(0, sr.getScaledHeight() - 35,(int)calc,sr.getScaledHeight() - 30,new Color(200,149,255,250).getRGB());

    }
    private static void resetTextureState(){
        GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
    }
}
