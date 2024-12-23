package cn.trystage.command.commands;

import cn.trystage.utils.PlayerUtils;
import com.yumegod.obfuscation.Native;
import org.lwjgl.input.Keyboard;

import cn.trystage.Client;
import cn.trystage.command.Command;
import cn.trystage.module.Module;


public class BindCommand implements Command {

    @Override
    public boolean run(String[] args) {
        if (args.length == 3) {

            Module m = Client.INSTANCE.moduleManager.getModule(args[1]);

            if (m == null) {
                PlayerUtils.tellPlayer("No Module Called That");
                return false;
            }
            m.setKeyCode(Keyboard.getKeyIndex(args[2].toUpperCase()));
            PlayerUtils.tellPlayer(m.getName() + " has been bound to " + args[2] + ".");
            return true;
        }
        return false;
    }

    @Override
    public String usage() {
        return "USAGE: -bind [module] [key]";
    }

}
