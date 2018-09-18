package com.pbelov.java.tg.quest_bot;

import com.pbelov.java.tg.quest_bot.Utils.StringUtils;
import com.pbelov.java.tg.quest_bot.Utils.Utils;
import pro.zackpollard.telegrambot.api.chat.message.Message;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.user.User;

@SuppressWarnings("ResultOfMethodCallIgnored")
class BaseEventsHelper {
    private static final String TAG = "BaseEventsHelper";
    static String chatName = null;
    static String messageText = null;
    static String senderUserName;
    static Message message;
    static User sender;

    static String argsString;

    static void getBaseData(TextMessageReceivedEvent event, String type) {
        chatName = event.getChat().getName();
        message = event.getMessage();
        sender = message.getSender();
        senderUserName = sender.getUsername();
        String personName = sender.getFullName() + " (" + senderUserName + ")";
        messageText = event.getContent().getContent().trim();

        Utils.println(TAG, "[" + type + "] " + chatName + ", " + StringUtils.getCurrentTimeStamp() + "; " + personName + ": " + messageText, chatName);
    }
}
