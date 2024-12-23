package cn.trystage.command.commands;

import cn.trystage.Client;
import cn.trystage.command.Command;
import cn.trystage.utils.PlayerUtils;
import com.yumegod.obfuscation.Native;


public class HelpCommand implements Command {

    @Override
    public boolean run(String[] args) {
        PlayerUtils.tellPlayer("Here are the list of commands:");
        for (Command c : Client.INSTANCE.commandManager.getCommands().values()) {
            PlayerUtils.tellPlayer(c.usage());
        }
        return true;
    }

    @Override
    public String usage() {
        return "USAGE: -help";
    }

}
