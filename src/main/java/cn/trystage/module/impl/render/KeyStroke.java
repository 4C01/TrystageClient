package cn.trystage.module.impl.render;

import cn.trystage.annotations.event.EventTarget;
import cn.trystage.event.impl.misc.EventKeyboard;
import cn.trystage.event.impl.render.EventRender2D;
import cn.trystage.module.Category;
import cn.trystage.module.Module;
import cn.trystage.ui.font.FontManager;
import cn.trystage.utils.RoundedUtil;
import cn.trystage.value.impl.BooleanValue;
import cn.trystage.value.impl.NumberValue;
import com.yumegod.obfuscation.Native;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@Native
public class KeyStroke extends Module {
    public NumberValue x = new NumberValue("x", 100, 0, 1000, 2);
    public NumberValue y = new NumberValue("y", 110, 0, 500, 2);
    public NumberValue size = new NumberValue("Size", 20, 10, 500, 5);
    public NumberValue corner = new NumberValue("Corner Size", 5, 0, 20, 1);
    private final BooleanValue showSpace = new BooleanValue("Show Space",true);
    private final BooleanValue text = new BooleanValue("Text",true);
    boolean onUp = false;
    boolean onDown = false;
    boolean onLeft = false;
    boolean onRight = false;
    boolean onSpace = false;
    public KeyStroke(){
        super("KeyStroke", Category.RENDER);
        this.setState(true);
    }

    @EventTarget
    public void onRender2D(EventRender2D event){

        String up = text.getValue()? "W" : "";
        String down = text.getValue()? "S" : "";
        String left = text.getValue()? "A" : "";
        String right = text.getValue()? "D" : "";
        String space = text.getValue()? "-" : "";

        // w
        RoundedUtil.drawRound(x.getValue().floatValue() + size.getValue().floatValue(),
                y.getValue().floatValue(),
                size.getValue().floatValue(),
                size.getValue().floatValue(),
                corner.getValue().floatValue(),
                onUp? new Color(0,0,0,30) : new Color(0,0,0,75));

        FontManager.font20.drawString(up,
                x.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(up) / 2.0,
                y.getValue().floatValue() + 0.5 * size.getValue().floatValue() - FontManager.font20.getWidth(up) / 2.0,
                -1);
        // a
        RoundedUtil.drawRound(x.getValue().floatValue(),
                y.getValue().floatValue() + size.getValue().floatValue(),
                size.getValue().floatValue(),
                size.getValue().floatValue(),
                corner.getValue().floatValue(),
                onLeft? new Color(0,0,0,30) : new Color(0,0,0,75));

        FontManager.font20.drawString(left,
                x.getValue().floatValue() + 0.5 * size.getValue().floatValue() - FontManager.font20.getWidth(left) / 2.0,
                y.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(left) / 2.0,
                -1);
        // s
        RoundedUtil.drawRound(x.getValue().floatValue() + size.getValue().floatValue(),
                y.getValue().floatValue() + size.getValue().floatValue(),
                size.getValue().floatValue(),
                size.getValue().floatValue(),
                corner.getValue().floatValue(),
                onDown? new Color(0,0,0,30) : new Color(0,0,0,75));

        FontManager.font20.drawString(down,
                x.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(down) / 2.0,
                y.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(down) / 2.0,
                -1);

        // d
        RoundedUtil.drawRound(x.getValue().floatValue() + size.getValue().floatValue() + size.getValue().floatValue(),
                y.getValue().floatValue() + size.getValue().floatValue(),
                size.getValue().floatValue(),
                size.getValue().floatValue(),
                corner.getValue().floatValue(),
                onRight? new Color(0,0,0,30) : new Color(0,0,0,75));

        FontManager.font20.drawString(right,
                x.getValue().floatValue() + size.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(right) / 2.0,
                y.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(right) / 2.0,
                -1);

        if (showSpace.getValue()){
            // space
            RoundedUtil.drawRound(x.getValue().floatValue(),
                    y.getValue().floatValue() + size.getValue().floatValue() + size.getValue().floatValue(),
                    size.getValue().floatValue() * 3,
                    size.getValue().floatValue() / 2,
                    corner.getValue().floatValue(),
                    onSpace? new Color(0,0,0,30) : new Color(0,0,0,75));

            FontManager.font20.drawString(space,
                    x.getValue().floatValue() + 1.5 * size.getValue().floatValue() - FontManager.font20.getWidth(space) / 2.0,
                    y.getValue().floatValue() + size.getValue().floatValue() + 1.20 * size.getValue().floatValue() - FontManager.font20.getWidth(space) / 2.0,
                    -1);

        }
    }

    @EventTarget
    public void onKey(EventKeyboard keyboard){
        int eventKey = keyboard.getKey();
        if (eventKey < 0 || eventKey >= Keyboard.KEYBOARD_SIZE) {
            return;
        }
        if (Keyboard.isKeyDown(eventKey)){
            if (eventKey == mc.gameSettings.keyBindForward.getKeyCode()){
                onUp = true;
            }
            if (eventKey == mc.gameSettings.keyBindBack.getKeyCode()){
                onDown = true;
            }
            if (eventKey == mc.gameSettings.keyBindLeft.getKeyCode()){
                onLeft = true;
            }
            if (eventKey == mc.gameSettings.keyBindRight.getKeyCode()){
                onRight = true;
            }
            if (eventKey == mc.gameSettings.keyBindJump.getKeyCode()){
                onSpace = true;
            }
        }else{
            if (eventKey == mc.gameSettings.keyBindForward.getKeyCode()){
                onUp = false;
            }
            if (eventKey == mc.gameSettings.keyBindBack.getKeyCode()){
                onDown = false;
            }
            if (eventKey == mc.gameSettings.keyBindLeft.getKeyCode()){
                onLeft = false;
            }
            if (eventKey == mc.gameSettings.keyBindRight.getKeyCode()){
                onRight = false;
            }
            if (eventKey == mc.gameSettings.keyBindJump.getKeyCode()){
                onSpace = false;
            }
        }
    }

}
