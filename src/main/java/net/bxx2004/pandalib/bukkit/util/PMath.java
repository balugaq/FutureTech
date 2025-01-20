package net.bxx2004.pandalib.bukkit.util;

import net.bxx2004.pandalib.bukkit.language.abandon.PAction;
import net.bxx2004.pandalib.bukkit.language.abandon.PMessage;
import net.bxx2004.pandalib.bukkit.manager.Lang;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数学工具
 */
public class PMath {
    /**
     * 随机数
     */
    private static Random random = new Random();

    public static int formatNumber(int number, int number2) {
        if ((number % number2) != 0) {
            return (number / number2) + 1;
        }
        return 1;
    }

    public static String progress(int index, String content) {
        String a = content.substring(0, index);
        String b = content.substring(index);
        return PMessage.replace("&c" + a + "&7" + b);
    }

    public static boolean sum(Player player, String term) {
        try {
            if (term.contains("|")) {
                String[] a = term.split("\\|");
                for (String b : a) {
                    boolean result = Boolean.valueOf(PAction.go(b.trim(), player).toString());
                    if (result) {
                        return true;
                    }
                }
                return false;
            }
            if (term.contains("&")) {
                String[] a = term.split("&");
                for (String b : a) {
                    boolean result = Boolean.valueOf(PAction.go(b.trim(), player).toString());
                    if (!result) {
                        return false;
                    }
                }
                return true;
            }
            boolean result = Boolean.valueOf(PAction.go(term.trim(), player).toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Lang.error("&4条件计算异常", term);
        }
        return false;
    }

    public static void regx(String regx, String body, Consumer<String> consumer) {
        Pattern pattern = Pattern.compile(regx);
        Matcher m = pattern.matcher(body);
        while (m.find()) {
            consumer.accept(m.group());
        }
    }

    public static String sumString(String... strings) {
        String a = "";
        for (String b : strings
        ) {
            a = a + b;
        }
        return a;
    }

    /**
     * 获得范围内的随机整数
     *
     * @param max 最大值
     * @param min 最小值
     * @return 随机整数
     */
    public static int getRandomAsInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 获得范围内的随机小数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机小数
     */
    public static double getRandomAsDouble(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    /**
     * Int整数求和
     *
     * @param number Int
     * @return 和
     */
    @Deprecated
    public static int sum(int... number) {
        int b = 0;
        for (int a : number) {
            b = b + a;
        }
        return b;
    }

    /**
     * 从一个数组里面获取随机字符串
     *
     * @param strings 数组
     * @return 随机的字符串
     */
    public static String getRandomAsString(String... strings) {
        return strings[PMath.getRandomAsInt(0, strings.length - 1)];
    }

    /**
     * 将小数保留小数点后两位
     *
     * @param x 小数
     * @return 格式化的字符串
     */
    public static String formatDouble(double x) {
        return String.format("%.2f", x);
    }

    public static String[] toStringArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static List<String> toStringList(String... array) {
        List<String> list = new ArrayList<>();
        for (String s : array) {
            list.add(s);
        }
        return list;
    }
}
