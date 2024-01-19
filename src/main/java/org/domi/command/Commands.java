package org.domi.command;

import org.CatAndDomi.utils.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.domi.APITest;

import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    private static final APITest plugin = APITest.getInstance();
    public final List<String> list = Arrays.asList("인벤", "null", "no");
    public final List<String> list1 = List.of();

    public Commands() {
        plugin.getCommand("test").setExecutor(this);
        plugin.getCommand("test").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player p) {
                p.sendMessage("ㅇㅇ");
            }
        } else {
            if (sender instanceof Player p) {
                switch (args[0]) {
                    case "인벤":
                        ItemStack item = new ItemStack(Material.COD, 10);
                        if(InventoryUtils.hasEnoughSpace(p.getInventory().getStorageContents(), item)) {
                            p.getInventory().addItem(item);
                            p.sendTitle("", "인벤토리에 공간 있음!", 10, 10, 10);
                        }else{
                            p.sendMessage("인벤토리에 남은 공간이 없습니다!");
                        }
                        break;
                    case "null":
                        p.sendMessage("null");
                        break;
                    case "no":
                        p.sendMessage("no");
                        break;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return list;
        }
        return list1;
    }
}
