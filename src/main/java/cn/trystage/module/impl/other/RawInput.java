package cn.trystage.module.impl.other;

import cn.trystage.module.Category;
import cn.trystage.module.Module;
import com.yumegod.obfuscation.Native;
import lombok.Getter;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.lang.reflect.Constructor;

@Native
public class RawInput extends Module {
    public RawInput() {
        super("RawInput", Category.OTHER);
        this.setState(true);
    }

    @Getter
    private Mouse mouse;
    @Getter
    private volatile int dx = 0;
    @Getter
    private volatile int dy = 0;
    private volatile boolean running;
    private Thread inputThread;

    @SuppressWarnings("unchecked")
    private static ControllerEnvironment createDefaultEnvironment() throws ReflectiveOperationException {
        Constructor<ControllerEnvironment> constructor = (Constructor<ControllerEnvironment>)
                Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        running = true;
        inputThread = new Thread(() -> {
            while (running) {
                if (mouse == null) {
                    try {
                        Controller[] controllers = createDefaultEnvironment().getControllers();
                        for (Controller controller : controllers) {
                            if (controller.getType() == Controller.Type.MOUSE) {
                                controller.poll();
                                Mouse potentialMouse = (Mouse) controller;
                                if (potentialMouse.getX().getPollData() != 0.0 || potentialMouse.getY().getPollData() != 0.0) {
                                    mouse = potentialMouse;
                                    if (Minecraft.getMinecraft().thePlayer != null) {
                                        Minecraft.getMinecraft().thePlayer.addChatMessage(
                                                new ChatComponentText("Found mouse"));
                                    }
                                    break;
                                }
                            }
                        }
                    } catch (ReflectiveOperationException e) {
                        e.printStackTrace();
                    }
                } else {
                    mouse.poll();
                    dx += (int) mouse.getX().getPollData();
                    dy += (int) mouse.getY().getPollData();
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        inputThread.setName("RawInput-Thread");
        inputThread.setDaemon(true);
        inputThread.start();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        running = false;
        mouse = null;
        dx = 0;
        dy = 0;
    }

    public void reset() {
        dx = 0;
        dy = 0;
    }
}

