package cn.ChengZhiYa.BiliBiliBot;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class multi {

    public static String[] GetProxy() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).
                setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet = new HttpGet("http://www.66ip.cn/mo.php?tqsl=1");

        //伪装成合法浏览器
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54");

        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String ProxyMessage = EntityUtils.toString(httpResponse.getEntity());
            String[] ProxyString = ProxyMessage.
                    replaceAll("\n","#!#").
                    replaceAll("\r","").
                    replaceAll(" ","").
                    replaceAll("\t","").
                    replaceAll("<br/>","").
                    split("#!#");
            return ProxyString[13].split(":");
        }
        return null;
    }

    public static void ColorLog(String Message) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(ChatColor(Message));
    }

    public static String ChatColor(String Message) {
        return ChatColor.translateAlternateColorCodes('&',Message);
    }

    public static String Sha256(String Message) {
        String encodeStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(Message.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuffer = new StringBuilder();
            for (final byte aByte : messageDigest.digest()) {
                final String temp = Integer.toHexString(aByte & 0xFF);
                if (temp.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(temp);
            }
            encodeStr = stringBuffer.toString().toUpperCase();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static String getHWID() {
        return Sha256(System.getProperty("user.name") +
                System.getenv("COMPUTERNAME") +
                System.getenv("os") + " " +
                System.getProperty("os.name") +
                System.getenv("PROCESSOR_IDENTIFIER") +
                System.getProperty("os.arch") +
                System.getProperty("os.version") +
                System.getProperty("user.language") +
                System.getenv("SystemRoot") +
                System.getenv("HOMEDRIVE") +
                System.getenv("PROCESSOR_LEVEL") +
                System.getenv("PROCESSOR_REVISION") +
                System.getenv("PROCESSOR_IDENTIFIER") +
                System.getenv("PROCESSOR_ARCHITECTURE") +
                System.getenv("PROCESSOR_ARCHITEW6432") +
                System.getenv("NUMBER_OF_PROCESSORS") +
                "YZF-Core-LoveBaiShaZi");
    }

    public static void CheckHWID() {
        ColorLog("[YZF-Core]开始检查HWID...");
        try {
            URL url1 = new URL("https://chengzhinb.github.io/" + Sha256("CZHWID"));
            URLConnection urlConnection = url1.openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla");
            urlConnection.setReadTimeout(30000);
            urlConnection.setConnectTimeout(30000);
            InputStream in = url1.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            StringBuilder stringBuilder = new StringBuilder();
            while ((str = bufr.readLine()) != null) {
                stringBuilder.append(str);
            }
            if (!url1.getHost().equals("chengzhinb.github.io") && url1.getPort() != 443 && !url1.getPath().equals("/" + Sha256("CZHWID"))) {
                disablePlugin();
                return;
            }
            String getHWID = stringBuilder.toString().replace("<!--", "").replace("-->", "");
            String[] allHWID = getHWID.split("\\|");
            boolean notADD = true;
            for (String HWIDS : allHWID) {
                if (getHWID().equals(HWIDS)) {
                    notADD = false;
                    break;
                }
            }
            if (notADD) {
                ColorLog("[YZF-Core]无效的HWID! 请找作者添加你的HWID 作者QQ:292200693");
                ColorLog("[YZF-Core]HWID: " + getHWID());
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable tText = new StringSelection(getHWID());
                clip.setContents(tText, null);
                ColorLog("[YZF-Core]HWID成功复制到粘贴板");
                disablePlugin();
                return;
            } else {
                ColorLog("[YZF-Core]HWID验证成功!");
            }
            in.close();
            isr.close();
            bufr.close();
        } catch (Exception i) {
            i.printStackTrace();
            ColorLog("[YZF-Core]无法验证HWID!请反馈给作者 作者QQ:292200693");
            disablePlugin();
        }
    }

    public static void disablePlugin() {
        Bukkit.getPluginManager().disablePlugin(main.main);
    }
}
