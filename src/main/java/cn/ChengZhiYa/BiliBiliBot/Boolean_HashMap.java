package cn.ChengZhiYa.BiliBiliBot;

import java.util.HashMap;

public class Boolean_HashMap {
    static HashMap<String, Boolean> Temp = new HashMap<>();
    public static void set(String HashMapName, Boolean Value) {
        Temp.put(HashMapName,Value);
    }

    public static Boolean get(String HashMapName) {
        return Temp.get(HashMapName);
    }
}
