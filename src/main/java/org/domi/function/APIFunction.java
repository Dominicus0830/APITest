package org.domi.function;

import org.CatAndDomi.api.InventoryAPI;
import org.CatAndDomi.components.NBT;
import org.CatAndDomi.utils.ColorUtils;
import org.CatAndDomi.utils.DataUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.domi.APITest;

import java.util.HashMap;
import java.util.Map;

public class APIFunction {
    private static final APITest plugin = APITest.getInstance();
    private static final DataUtils data = plugin.data;

    public static void updateCurrentPage(InventoryAPI inv) {
        ItemStack[] tools = inv.getPageTools();
        ItemStack item = tools[4];
        ItemMeta im = item.getItemMeta();
        im.setDisplayName("§a현재 페이지: §f" + (inv.getCurrentPage() + 1));
        item.setItemMeta(im);
        tools[4] = item;
        inv.setPageTools(tools);
        inv.update();
    }

    public static void openStorage(Player p, Player target) {
        InventoryAPI inv = new InventoryAPI(null, ColorUtils.applyColor("test"), 54, true, plugin);
        inv.setPages(0);
        inv.setObj(target.getUniqueId());

        ItemStack pane = NBT.setStringTag(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), "page", "true");
        ItemStack block = NBT.setStringTag(new ItemStack(Material.BARRIER), "block", "true");
        ItemStack prev = NBT.setStringTag(new ItemStack(Material.PINK_DYE), "prev", "true");
        ItemStack current = NBT.setStringTag(new ItemStack(Material.PAPER), "current", "true");
        ItemMeta im = prev.getItemMeta();
        im.setDisplayName("이전 페이지");
        prev.setItemMeta(im);
        ItemStack next = NBT.setStringTag(new ItemStack(Material.LIME_DYE), "next", "true");
        im = next.getItemMeta();
        im.setDisplayName("다음 페이지");
        next.setItemMeta(im);
        im = current.getItemMeta();
        im.setDisplayName("§a현재 페이지: §f" + (inv.getCurrentPage() + 1));
        current.setItemMeta(im);
        im = block.getItemMeta();
        im.setDisplayName("§c소유하지 않은 슬롯");
        block.setItemMeta(im);
        inv.setPageTools(new ItemStack[]{pane, pane, prev, pane, current, pane, next, pane, pane});

        Map<Integer, ItemStack> items = new HashMap<>();
        int maxPages = 6;
        int count = 0;
        ItemStack[] contents = new ItemStack[45];
        inv.setPages(maxPages);
        for (int page = 0; page <= maxPages; page++) {
            for (int i = 0; i < 45; i++) {
                contents[i] = block;
                count++;
            }
            inv.setPageContent(page, contents);
            contents = new ItemStack[45];
        }
        inv.update();
        p.openInventory(inv);
    }


    public static void saveCurrentContents(InventoryAPI api) {
        int currentPage = api.getCurrentPage();
        ItemStack[] content = new ItemStack[45];
        for (int i = 0; i < 45; i++) {
            content[i] = api.getContents()[i];
        }
        api.getPageItems().remove(currentPage);
        api.getPageItems().put(currentPage, content);
    }
}
