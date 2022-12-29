package cn.ChengZhiYa.BiliBiliBot;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiGroup;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.List;

import static cn.ChengZhiYa.BiliBiliBot.BiliBiliAPI.*;

public class BaiShenYao extends BukkitRunnable {
    public static main plugin;
    public BaiShenYao(main main) { plugin=main; }

    @Override
    public void run() {
        int UID = 477332594;
        String UIDName = String.valueOf(UID);
        MiraiBot miraiBot = MiraiBot.getBot(3174943121L);
        MiraiGroup miraiGroup1 = miraiBot.getGroup(801329619L);
        MiraiGroup miraiGroup2 = miraiBot.getGroup(152848071L);
        try {
            String_HashMap.set(UIDName,获取个人信息JSON(UID));
            String B站名称 = 获取B站名称(String_HashMap.get(UIDName));
            boolean 直播状态 = 获取B站直播状态(String_HashMap.get(UIDName));
            List<String> 动态合集 = 获取B站动态合集(UID);
            assert 动态合集 != null;
            int B站最新动态类型 = 获取动态类型(动态合集.get(0));
            if (String_HashMap.get(UID + "_动态ID") != null) {
                if (!String_HashMap.get(UID + "_动态ID").equals(获取动态ID(动态合集.get(0)))) {
                    if (B站最新动态类型 == 4) {
                        String B站动态内容 = 获取动态内容(动态合集.get(0));
                        String B站动态链接 = 获取动态链接(动态合集.get(0));
                        String Message = B站名称 + " 发布了新动态\n" +
                                "动态内容:" + B站动态内容 + "\n" +
                                "动态链接: " + B站动态链接;
                        Bukkit.broadcastMessage(Message);
                        miraiGroup1.sendMessage(Message);
                        miraiGroup2.sendMessage(Message);
                    } //发布动态
                    if (B站最新动态类型 == 8) {
                        String 最新视频标题 = 获取最新视频标题(动态合集.get(0));
                        String 最新视频简介 = 获取最新视频简介(动态合集.get(0));
                        String 最新视频链接 = 获取最新视频链接(动态合集.get(0));
                        String 最新视频视频分类 = 获取最新视频视频分类(动态合集.get(0));
                        int 最新视频播放量 = 获取最新视频播放量(动态合集.get(0));
                        int 最新视频点赞量 = 获取最新视频点赞量(动态合集.get(0));
                        int 最新视频投币量 = 获取最新视频投币量(动态合集.get(0));
                        String Message = B站名称 + " 发布了新视频\n" +
                                "视频标题: " + 最新视频标题 + " 分类: " + 最新视频视频分类 + "\n" +
                                "播放量: " + 最新视频播放量 + " 点赞量: " + 最新视频点赞量 + " 投币量: " + 最新视频投币量 + "\n" +
                                "视频链接: " + 最新视频链接 + "\n" +
                                "视频简介: " + 最新视频简介;
                        Bukkit.broadcastMessage(Message);
                        miraiGroup1.sendMessage(Message);
                        miraiGroup2.sendMessage(Message);
                    } //发布视频
                }
            }
            if (Boolean_HashMap.get(UID + "_直播状态") != null) {
                if (Boolean_HashMap.get(UID + "_直播状态") != 直播状态) {
                    String 直播间标题 = 获取B站直播间标题(String_HashMap.get(UIDName));
                    String 直播间链接 = 获取B站直播间链接(String_HashMap.get(UIDName));
                    String Message;
                    if (直播状态) {
                        Message = B站名称 + " 开播啦\n" +
                                "直播间标题: " + 直播间标题 + "\n" +
                                "直播间链接: " + 直播间链接;
                    }else {
                        Message = B站名称 + " 下播啦\n" +
                                "直播间标题: " + 直播间标题 + "\n" +
                                "直播间链接: " + 直播间链接;
                    }
                    Bukkit.broadcastMessage(Message);
                    miraiGroup1.sendMessage(Message);
                    miraiGroup2.sendMessage(Message);
                }
            }
            String_HashMap.set(UID + "_动态ID", 获取动态ID(动态合集.get(0)));
            Boolean_HashMap.set(UID + "_直播状态", 直播状态);
        } catch (IOException ignored) {}
    }
}
