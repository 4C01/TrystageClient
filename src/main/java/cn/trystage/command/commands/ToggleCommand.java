package cn.trystage.command.commands;

import cn.trystage.Client;
import cn.trystage.command.Command;
import cn.trystage.module.Module;
import cn.trystage.module.ModuleManager;
import cn.trystage.utils.PlayerUtils;
import com.yumegod.obfuscation.Native;


public class ToggleCommand implements Command {

    @Override
    public boolean run(String[] args) {

        if (args.length == 2) {

            Module module = Client.INSTANCE.moduleManager.getModule(args[1]);

            if (module == null) {
                PlayerUtils.tellPlayer("The module with the name " + args[1] + " does not exist.");
                return true;
            }

            module.toggle();

            return true;
        }


        return false;
    }

    @Override
    public String usage() {
        return "USAGE: -toggle [module]";
    }
}
