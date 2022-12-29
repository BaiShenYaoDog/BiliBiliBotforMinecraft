package cn.ChengZhiYa.BiliBiliBot;


import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import static cn.ChengZhiYa.BiliBiliBot.multi.*;

public final class main extends JavaPlugin {

    public static main main;

    @Override
    public void onEnable() {
        // Plugin startup logic
        CheckHWID();
        ColorLog("&7---------&6B站互通插件&7---------");
        ColorLog("&6作者: ChengZhiYa QQ:292200693");
        ColorLog("&e非常感谢 夜之粉所有人员!");
        BukkitTask BaiShenYao_Task = new BaiShenYao(this).runTaskTimer(this, 0L, 300L);
        BukkitTask XiaoYe_Task = new XiaoYe(this).runTaskTimer(this, 0L, 400L);
        ColorLog("&a插件启动!");
        ColorLog("&7---------&6B站互通插件&7---------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ColorLog("&7---------&6B站互通插件&7---------");
        ColorLog("&6作者: ChengZhiYa QQ:292200693");
        ColorLog("&e非常感谢 夜之粉所有人员!");
        ColorLog("&a插件已关闭!");
        ColorLog("&7---------&6B站互通插件&7---------");
    }
}
