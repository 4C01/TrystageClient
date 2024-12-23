package cn.trystage.module;

import cn.trystage.Client;
import cn.trystage.data;
import cn.trystage.utils.SoundUtils;
import cn.trystage.value.AbstractValue;
import cn.trystage.value.impl.BooleanValue;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Module {

    public Minecraft mc = Minecraft.getMinecraft();

    public String name;
    public Category category;
    public boolean state = false;
    public ArrayList<AbstractValue<?>> values;
    public int key;

    public Module(String name, Category category){
        this.name = name;
        this.category = category;
        this.key = Keyboard.KEY_NONE;
        this.values = new ArrayList<>();
    }

    public Module(String name, Category category, int k){
        this.name = name;
        this.category = category;
        this.key = k;
        this.values = new ArrayList<>();
    }

    public ArrayList<AbstractValue<?>> getValues() {
        return values;
    }

    public void setValues(ArrayList<AbstractValue<?>> values) {
        this.values = values;
        this.onSetValues();
    }

    public boolean isToggle() {
        return state;
    }

    public void setToggle(boolean toggle) {
        this.state = toggle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeyCode() {
        return key;
    }

    public void setKeyCode(int keyCode) {
        this.key = keyCode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void toggle()
    {
        this.state = !this.state;
        if(this.state) onEnable(); else onDisable();
    }

    public void onEnable() {
        Client.INSTANCE.eventManager.register(this);
        SoundUtils.playSound(new ResourceLocation("Trystage/sounds/toggle/enable.wav"), data.getToggleSoundVolume());
    }

    public void onDisable() {
        Client.INSTANCE.eventManager.unregister(this);
        SoundUtils.playSound(new ResourceLocation("Trystage/sounds/toggle/disable.wav"),data.getToggleSoundVolume());
    }
//
    public void setState(boolean state) {
        this.state = state;
        if(state){
            onEnable();
        }
        else{
            onDisable();
        }
    }

    public void onSetValues(){

    }
}
