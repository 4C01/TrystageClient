package net.minecraft.client.gui;

import cn.trystage.utils.HoveringUtil;
import cn.trystage.utils.RenderUtil;
import cn.trystage.utils.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiButtonLanguage extends GuiButton
{
    public GuiButtonLanguage(int buttonID, int xPos, int yPos)
    {
        super(buttonID, xPos, yPos, 20, 20, "");
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
//            mc.getTextureManager().bindTexture(GuiButton.buttonTextures);
//            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//            boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
//            int i = 106;
//
//            if (flag)
//            {
//                i += this.height;
//            }
//
//            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, i, this.width, this.height);
//            RoundedUtil.drawRound(xPosition, yPosition, width, height, (float)3.5, true, new Color(0,0,0,50));
            boolean hovered = HoveringUtil.isHovering(xPosition, yPosition, width, height, mouseX, mouseY);
            RoundedUtil.drawRound(this.xPosition, this.yPosition, this.width, this.height, (float)3.5, true, new Color(0,0,0,50));
            if(hovered){
                RoundedUtil.drawRound(this.xPosition, this.yPosition, this.width, this.height, (float)3.5, true, new Color(200,200,200,50));
            }

            RenderUtil.drawCustomImage(this.xPosition + 4 , this.yPosition + 4, this.width - 8 , this.height - 8 ,new ResourceLocation("Trystage/images/lang.png"));
        }
    }
}
