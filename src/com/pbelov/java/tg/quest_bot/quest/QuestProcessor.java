package com.pbelov.java.tg.quest_bot.quest;

import com.pbelov.java.tg.quest_bot.Utils.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QuestProcessor {
    private static Quest quest = new Quest();
    final static String name = "quest_progress.dat";

    // userID / question number
    private static Map<Long, Integer> userQuestProgress = new HashMap<>();

    public static void restoreProgress() {
        if (userQuestProgress.isEmpty()) {
            userQuestProgress = loadQuestProgress();
        }
    }

    public static int getProgress(long userID) {
        if (!userQuestProgress.containsKey(userID)) {
            setProgress(userID, 0);
        }
        return userQuestProgress.get(userID);
    }

    public static String incrementProgress(long userID) {
        int currentProgress = getProgress(userID);
        if (currentProgress == quest.QAList.size() - 1) {
            setProgress(userID, -1);
            return "Congrats, you have finished this Quest!";
        } else {
            setProgress(userID, ++currentProgress);
            return getQuestion(userID, currentProgress);
        }
    }

    public static void setProgress(long userID, int questionNumber) {
        userQuestProgress.put(userID, questionNumber);
        saveQuestProgress();
    }

    public static String getLatestQuestion(long userID) {
        return getQuestion(userID, getProgress(userID));
    }

    public static String getQuestion(long userID, int questionNumber) {
        if (questionNumber <= getProgress(userID)) {
            return quest.QAList.get(questionNumber).q;
        } else {
            Utils.println("Something goes wrong");
            return null;
        }
    }

    public static Boolean verifyAnswer(long userID, String answer) {
        int currentProgress = getProgress(userID);
        if (currentProgress != -1) {
            return quest.QAList.get(getProgress(userID)).a.equals(answer);
        } else {
            return null;
        }
    }

    private static void saveQuestProgress() {
        File file = new File(name);
        if (file.exists()) {
            file.delete();
        }

        ObjectOutputStream oos = null;
        FileOutputStream fout;
        try {
            fout = new FileOutputStream(name, true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(userQuestProgress);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static HashMap<Long, Integer> loadQuestProgress() {
        HashMap<Long, Integer> readCase = null;

        if (new File(name).exists()) {
            ObjectInputStream objectinputstream = null;
            try {
                FileInputStream streamIn = new FileInputStream(name);
                objectinputstream = new ObjectInputStream(streamIn);
                readCase = (HashMap<Long, Integer>) objectinputstream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (objectinputstream != null) {
                    try {
                        objectinputstream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return readCase;
    }
}
