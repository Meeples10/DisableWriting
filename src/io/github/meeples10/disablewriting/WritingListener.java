package io.github.meeples10.disablewriting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class WritingListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if(Main.debug) log("[F] SignChangeEvent: " + e.getPlayer().getName());
        if(Main.disableSigns && !Main.canBypass(e.getPlayer().getName())) {
            e.setCancelled(true);
            if(Main.debug) log("[C] SignChangeEvent: " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onBookEdit(PlayerEditBookEvent e) {
        if(Main.debug) log("[F] PlayerEditBookEvent: " + e.getPlayer().getName());
        if(Main.disableBooks && !Main.canBypass(e.getPlayer().getName())) {
            int amount = e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().getHeldItemSlot())
                    .getAmount();
            e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().getHeldItemSlot(),
                    new ItemStack(Material.WRITABLE_BOOK, amount));
            e.setCancelled(true);
            if(Main.debug) log("[C] PlayerEditBookEvent: " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(Main.debug) log("[F] AsyncPlayerChatEvent: " + e.getPlayer().getName());
        if(Main.disableChat && !Main.canBypass(e.getPlayer().getName())) {
            e.setCancelled(true);
            if(Main.debug) log("[C] AsyncPlayerChatEvent: " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if((e.getItem() == null) || (e.getItem().getType() != Material.MAP)) return;
        if(Main.debug) log("[F] PlayerInteractEvent: " + e.getPlayer().getName());
        if(Main.disableMaps && !Main.canBypass(e.getPlayer().getName())) {
            e.setCancelled(true);
            if(Main.debug) log("[C] PlayerInteractEvent: " + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(Main.replaceJoinMessages) {
            e.setJoinMessage("§8[§a+§8] §7" + e.getPlayer().getName());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(Main.replaceJoinMessages) {
            e.setQuitMessage("§8[§c-§8] §7" + e.getPlayer().getName());
        }
    }

    private static void log(String s) {
        Bukkit.getPluginManager().getPlugin(Main.NAME).getLogger().info(s);
    }
}