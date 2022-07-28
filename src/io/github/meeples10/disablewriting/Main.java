package io.github.meeples10.disablewriting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static final String NAME = "DisableWriting";

    private static File df, cfg;
    public static boolean disableBooks;
    public static boolean disableSigns;
    public static boolean disableChat;
    public static boolean disableMaps;
    public static boolean replaceJoinMessages;
    public static boolean debug;
    public static List<String> bypass = new ArrayList<String>();

    @Override
    public void onEnable() {
        df = Bukkit.getServer().getPluginManager().getPlugin(NAME).getDataFolder();
        cfg = new File(df, "config.yml");
        this.getCommand("dw").setExecutor(new CommandMain());
        Bukkit.getPluginManager().registerEvents(new WritingListener(), Bukkit.getPluginManager().getPlugin(NAME));
        loadConfig();
    }

    public static boolean loadConfig() {
        try {
            if(!df.exists()) {
                df.mkdirs();
            }
            if(!cfg.exists()) {
                Bukkit.getPluginManager().getPlugin(NAME).saveDefaultConfig();
            }
            bypass.clear();
            FileConfiguration c = YamlConfiguration.loadConfiguration(cfg);
            disableBooks = c.getBoolean("disable-books");
            disableSigns = c.getBoolean("disable-signs");
            disableChat = c.getBoolean("disable-chat");
            disableMaps = c.getBoolean("disable-maps");
            replaceJoinMessages = c.getBoolean("replace-join-leave-messages");
            for(String s : c.getStringList("bypass-players")) {
                bypass.add(s);
            }
            debug = c.getBoolean("debug");
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean canBypass(String name) {
        for(String s : bypass) {
            if(name.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public static void toggleBooks() {
        disableBooks = !disableBooks;
        saveStates();
    }

    public static void toggleSigns() {
        disableSigns = !disableSigns;
        saveStates();
    }

    public static void toggleChat() {
        disableChat = !disableChat;
        saveStates();
    }

    public static void toggleMaps() {
        disableMaps = !disableMaps;
        saveStates();
    }

    public static void toggleJoinMessages() {
        replaceJoinMessages = !replaceJoinMessages;
        saveStates();
    }

    private static void saveStates() {
        FileConfiguration c = YamlConfiguration.loadConfiguration(cfg);
        c.set("disable-books", disableBooks);
        c.set("disable-signs", disableSigns);
        c.set("disable-chat", disableChat);
        c.set("disable-maps", disableMaps);
        c.set("replace-join-leave-messages", replaceJoinMessages);
    }
}