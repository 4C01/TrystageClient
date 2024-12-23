package cn.trystage.ui.clickgui.values;
import cn.trystage.utils.RenderUtil;
import cn.trystage.value.impl.*;
import cn.trystage.value.AbstractValue;
import com.yumegod.obfuscation.Native;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.math.BigDecimal;

import static cn.trystage.ui.clickgui.MainWindow.mainColor;


public class NumberRender extends ValueRender {
    public NumberRender(AbstractValue v, float x, float y) {
        super(v, x, y);
    }

    @Override
    public void onRender(float valueX, float valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
        float present = 90 * (((Number) value.getValue()).floatValue() - ((NumberValue) value).getMinimum().floatValue()) / (((NumberValue) value).getMaximum().floatValue() - ((NumberValue) value).getMinimum().floatValue());
        Minecraft.getMinecraft().fontRendererObj.drawString(value.getName(), (int) (x + 5), (int) (valueY - 5), new Color(115,115,115).getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawString(value.getValue().toString(), (int) (x + 95 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(value.getValue().toString())), (int) (valueY - 5), new Color(115,115,115).getRGB());
//        value.getValue() = present;


        RenderUtil.drawRect(x + 5, valueY + 3, x + 95, valueY + 4.5f, new Color(222, 222, 222));
        RenderUtil.drawRect(x + 5, valueY + 3, x + 5 + ((Number) value.getValue()).intValue(), valueY + 4.5f, mainColor);

        if ((isHovered(x + 5, valueY + 0.5f, x + 95, valueY + 6.5f, mouseX, mouseY)) && Mouse.isButtonDown(0)) {
//            value.drag = true;
            float render2 = ((NumberValue) value).getMinimum().floatValue();
            double max = ((NumberValue) value).getMaximum().doubleValue();
            float inc = (((NumberValue) value).getIncrement()).floatValue();
            double valAbs = (double) mouseX - x - 5;
            double perc = valAbs / (((x + 95) - (x + 5)));
            perc = Math.min(Math.max(0.0D, perc), 1.0D);
            double valRel = (max - render2) * perc;
            float val = (float) (render2 + valRel);
            val = (float) (Math.round(val * (1.0D / inc)) / (1.0D / inc));
            BigDecimal b = new BigDecimal(val);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            ((NumberValue) value).setValue(f1);

            RenderUtil.smoothCircle(x + 5 + ((Number) value.getValue()).intValue(), valueY + 3.5f, 4, mainColor);
            RenderUtil.smoothCircle(x + 5 + ((Number) value.getValue()).intValue(), valueY + 3.5f, 3, -1);
        } else {

            RenderUtil.smoothCircle(x + 5 + ((Number) value.getValue()).intValue(), valueY + 3.5f, 4, new Color(200, 200, 200).getRGB());
            RenderUtil.smoothCircle(x + 5 + ((Number) value.getValue()).intValue(), valueY + 3.5f, 3, -1);
        }

    }

    @Override
    public void onClicked(float x, float y, int clickType) {

    }

    @Override
    public void onMouseMove(float x, float y, int clickType) {
        mouseX = x;
        mouseY = y;
//        if (value.drag && !Mouse.isButtonDown(0)) {
//            value.drag = false;
//        }

    }
}
