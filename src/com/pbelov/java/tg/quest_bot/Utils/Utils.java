package com.pbelov.java.tg.quest_bot.Utils;

import com.pbelov.java.tg.quest_bot.Main;

import java.io.File;
import java.util.List;

import static com.pbelov.java.tg.quest_bot.Utils.FileUtils.WORKING_DIR;

public class Utils {
    private static File logFile = new File("log.txt");
    public static final String LOGDIR = "logs";
    private static boolean logFileDeleted = false;

    private Utils() {
    }

    private static void checkFile(File file) {
        if (file == null) {
            return;
        } else {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
        }
    }

    private static void checkLogFile() {
        if (!logFileDeleted) {
            if (!logFile.exists() || logFile.delete()) {
                logFileDeleted = true;
            } else {
                System.out.println("Unable to delete log file " + logFile.getName());
                System.exit(-1);
            }
        }
    }

    public static String fixFileName(String filename) {
        return filename.replaceAll(":", "");
    }

    public static void println(String str) {
        System.out.println(str);
    }

        public static void println(String tag, String str, String filename) {
        filename = fixFileName(filename);
        checkFile(new File(WORKING_DIR + File.separator + LOGDIR, filename));
        System.out.println(Main.DEBUG ? tag + ": " + str : str);
        FileUtils.appendStringToFile(str + "\r\n", new File(LOGDIR, filename + ".log"));
    }

    public static void println(String tag, String str) {
        checkLogFile();

        //noinspection ConstantConditions
        System.out.println(Main.DEBUG ? tag + ": " + str : str);
        FileUtils.appendStringToFile(str + "\r\n", logFile);
    }

    static void error(String tag, String str) {
        checkLogFile();

        if (Main.DEBUG) {
            System.err.println(tag + ": " + str);
        } else {
            System.err.println(str);
        }
        FileUtils.appendStringToFile("ERROR: " + str + "\r\n", logFile);
    }

    public static void addToArrayList(final List<String> list, String value) {
        if (!list.contains(value)) {
            list.add(value);
        }
    }
}
