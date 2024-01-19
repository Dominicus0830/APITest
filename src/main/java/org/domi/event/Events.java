package org.domi.event;

import org.CatAndDomi.api.InventoryAPI;
import org.CatAndDomi.components.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.domi.APITest;
import org.domi.function.APIFunction;

public class Events implements Listener {
    private static final APITest plugin = APITest.getInstance();

    public Events() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void ShiftF(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        if (e.getPlayer().isSneaking()) {
            APIFunction.openStorage(player, player);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() instanceof InventoryAPI api) {
            if (api.isValidHandler(plugin)) {
                System.out.println("valid");
                if (e.getCurrentItem() != null) {
                    System.out.println("not null");
                    if (NBT.hasTagKey(e.getCurrentItem(), "block")) {
                        e.setCancelled(true);
                        return;
                    }
                    if (NBT.hasTagKey(e.getCurrentItem(), "page")) {
                        e.setCancelled(true);
                        return;
                    }
                    if (NBT.hasTagKey(e.getCurrentItem(), "prev")) {
                        System.out.println("prev");
                        e.setCancelled(true);
                        APIFunction.saveCurrentContents(api);
                        if (api.prevPage()) {
                            APIFunction.updateCurrentPage(api);
                        }
                        return;
                    }
                    if (NBT.hasTagKey(e.getCurrentItem(), "next")) {
                        System.out.println("next");
                        e.setCancelled(true);
                        APIFunction.saveCurrentContents(api);
                        if (api.nextPage()) {
                            APIFunction.updateCurrentPage(api);
                        }
                    }
                }
            }
        }
    }
}
