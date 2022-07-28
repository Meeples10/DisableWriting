package io.github.meeples10.disablewriting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class CommandMain implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("disablewriting.admin")) {
            if(args.length > 0) {
                switch(args[0].toLowerCase()) {
                case "reload":
                    Main.loadConfig();
                    sender.sendMessage("[§3" + Main.NAME + "§f] Config reloaded");
                    break;
                case "bypass":
                    String list = "";
                    if(Main.bypass.size() == 0) {
                        list = "§7(none)";
                    } else {
                        for(String s : Main.bypass) {
                            list += "§f, §b" + s;
                        }
                    }
                    sender.sendMessage("[§3" + Main.NAME + "§f] The following players may bypass writing restrictions: "
                            + list.substring(4));
                    break;
                case "togglebooks":
                    Main.toggleBooks();
                    sender.sendMessage("[§3" + Main.NAME + "§f] Books are now " + format(Main.disableBooks));
                    break;
                case "togglesigns":
                    Main.toggleSigns();
                    sender.sendMessage("[§3" + Main.NAME + "§f] Signs are now " + format(Main.disableSigns));
                    break;
                case "togglechat":
                    Main.toggleChat();
                    sender.sendMessage("[§3" + Main.NAME + "§f] Chat is now " + format(Main.disableChat));
                    break;
                case "togglemaps":
                    Main.toggleMaps();
                    sender.sendMessage("[§3" + Main.NAME + "§f] Maps are now " + format(Main.disableMaps));
                    break;
                case "togglemessages":
                    Main.toggleJoinMessages();
                    sender.sendMessage("[§3" + Main.NAME + "§f] Short join/leave messages are now "
                            + format(Main.replaceJoinMessages));
                    break;
                case "show":
                    sender.sendMessage("[§3" + Main.NAME + "§f] Books are " + format(Main.disableBooks));
                    sender.sendMessage("[§3" + Main.NAME + "§f] Signs are " + format(Main.disableSigns));
                    sender.sendMessage("[§3" + Main.NAME + "§f] Chat is " + format(Main.disableChat));
                    sender.sendMessage("[§3" + Main.NAME + "§f] Maps are " + format(Main.disableMaps));
                    sender.sendMessage("[§3" + Main.NAME + "§f] Short join/leave messages are now "
                            + format(Main.replaceJoinMessages));
                    break;
                default:
                    return false;
                }
            } else {
                sender.sendMessage("§bDisableWriting Help§f\n" + "\u23F5 /dw§f: Show this message\n"
                        + "\u23F5 §3/dw reload§f: Reload the plugin\n"
                        + "\u23F5 §3/dw bypass§f: List players that can bypass writing restrictions\n"
                        + "\u23F5 §3/dw togglebooks§f: Enable/disable books\n"
                        + "\u23F5 §3/dw togglesigns§f: Enable/disable signs\n"
                        + "\u23F5 §3/dw togglechat§f: Enable/disable chat\n"
                        + "\u23F5 §3/dw togglemaps§f: Enable/disable maps\n"
                        + "\u23F5 §3/dw togglemessages§f: Enable/disable short join/leave messages\n"
                        + "\u23F5 §3/dw show§f: Show which items are enabled/disabled");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }
        return true;
    }

    private static String format(boolean disabled) {
        return disabled ? "§cdisabled" : "§aenabled";
    }
}