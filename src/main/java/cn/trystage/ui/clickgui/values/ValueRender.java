package cn.trystage.ui.clickgui.values;

import cn.trystage.ui.clickgui.TimerUtil;
import cn.trystage.value.AbstractValue;
import com.yumegod.obfuscation.Native;


public class ValueRender {
    public AbstractValue value;
    public float x, y;
    public float mouseX, mouseY, valueX, valueY;
    public TimerUtil timerUtil = new TimerUtil();

    public ValueRender(AbstractValue v, float x, float y) {
        this.value = v;
        this.x = x;
        this.y = y;
    }

    public void onRender(float valueX,float valueY) {

    }

    public void onClicked(float x, float y, int clickType) {

    }

    public void onMouseMove(float x, float y, int clickType) {


    }

    public float getX() {
        return x;
    }

    public AbstractValue getValue() {
        return value;
    }

    public float getY() {
        return y;
    }

    public void setValue(AbstractValue value) {
        this.value = value;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public static boolean isHovered(float x, float y, float x1, float y1, float mouseX, float mouseY) {
        if (mouseX > x && mouseY > y && mouseX < x1 && mouseY < y1) {
            return true;
        }
        return false;
    }

}
