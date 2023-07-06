package FM_Pegatour_Server.sys;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;
import java.io.*;
import java.util.regex.*;

public class IO { // 系统IO，属于工具类，故类内均为静态方法和变量

    // 静态变量
    public static final String SEP_MENU = "*"; // 分隔符种类
    public static final String SEP_PROMPT = "=";
    private static final int SEP_LENGTH = 70; // 分隔符长度

    public static final String USER = "save.obj"; // 用户配置文件
    public static final String DEFAULT = "default.obj"; // 用默认配置文件
    private static final File SAVE = new File( // 存档文件
        System.getProperty("user.dir") +
        File.separator +
        System.getProperty("java.class.path")
    );
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    private static Scanner input = new Scanner(System.in); // 获取输入

    public static int getInt(String prompt, int min, int max) { // 获取整型，带有范围检测
        int val;

        while (true) {
            try {
                print(prompt + ": "); // 输出提示
                val = input.nextInt();
                if (val < min || val > max) throw new IllegalArgumentException(); // 若超出范围则报错
                input.nextLine();
                return val;
            } catch (InputMismatchException e) { // 若输入不合要求
                println("输入不合法，请重新输入");
                input.nextLine(); // 继续
            } catch (IllegalArgumentException e) { // 若输入超出范围
                println(String.format("输入超出范围 (%d - %d)", min, max));
            }
        }
    }

    public static float getFloat(String prompt, float min) { // 获取浮点型，带有范围检测
        float val;

        while (true) {
            try {
                print(prompt + ": "); // 输出提示
                val = input.nextFloat();
                if (val < min) throw new IllegalArgumentException(); // 若超出范围则报错
                input.nextLine();
                return val;
            } catch (InputMismatchException e) { // 若输入不合要求
                println("输入不合法，请重新输入");
                input.nextLine(); // 继续
            } catch (IllegalArgumentException e) { // 若输入超出范围
                println(String.format("输入超出范围 (>%.1f)", min));
            }
        }
    }

    public static String getStr(String prompt, Integer length) { // 获取字符串
        String val;

        while (true) {
            print(prompt + ": ");
            val = input.nextLine();
            if (val.length() == 0) {
                println("输入不能为空");
            } else if (length != null && val.length() != length) {
                println("输入长度为 " + length + " 的字符");
            } else {
                return val;
            }
        }
    }

    public static Boolean getBool(String prompt) { // 获取布尔值
        String val;

        print(prompt + "\n(输入为空、为0或false均视为假，其余情况视为真): ");
        val = input.nextLine();
        if (val == "" || val == "0" || val == "false") {
            return false;
        } else {
            return true;
        }
    }

    public static String getStrMatch(String prompt, String pattern) {
        String val;

        while (true) {
            print(prompt + ": ");
            val = input.nextLine();
            if (Pattern.matches(pattern, val)) {
                return val;
            } else {
                println("输入不合法，请重新输入");
            }
        }
    }

    public static String tableEntry(String cosparid, String name, String track, String active) { // 表项构造器
        return String.format("%-8s\t%-5s\t%-5s\t%-5s", cosparid, name, track, active);
    }

    public static void printSep(String letter) { // 输出分隔符
        println(String.join("", Collections.nCopies(SEP_LENGTH, letter)));
    }

    public static void print(String str) { // 输出字符串
        System.out.print(str);
    }

    public static void print(int val) { // 输出整型
        System.out.print(val);
    }

    public static void println(String str) { // 输出字符串，末尾换行
        System.out.println(str);
    }

    public static void println(int val) { // 输出整型，末尾换行
        System.out.println(val);
    }

    public static boolean saveExists(String filename) {
        return new File(SAVE + File.separator + filename).exists();
    }

    // public static boolean save(String filename) {
    //     try {
    //         new File(SAVE + File.separator + filename).createNewFile();
    //         outputStream = new ObjectOutputStream(new FileOutputStream(SAVE + File.separator + filename));
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return Satellite.save(outputStream);
    // }
    
    // public static boolean read(String filename) {
    //     try {
    //         inputStream = new ObjectInputStream(new FileInputStream(SAVE + File.separator + filename));
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return Satellite.read(inputStream);
    // }
}