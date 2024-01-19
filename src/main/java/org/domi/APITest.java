package org.domi;

import org.CatAndDomi.utils.DataUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.domi.command.Commands;
import org.domi.event.Events;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class APITest extends JavaPlugin {
    private static APITest plugin;
    public static DataUtils data;
    public static APITest getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        data = new DataUtils(this);
        new Events();
        new Commands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}