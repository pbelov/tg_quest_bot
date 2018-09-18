package com.pbelov.java.tg.quest_bot;

import com.pbelov.java.tg.quest_bot.Utils.TgMsgUtil;
import com.pbelov.java.tg.quest_bot.quest.QuestProcessor;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

@SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored", "StatementWithEmptyBody"})
class MessageHelper extends BaseEventsHelper {
    private static final String TAG = "MessageHelper";

    private MessageHelper() {
    }

    //TODO: update
    static String getMessagesData(TextMessageReceivedEvent event) {
        getBaseData(event, "Message");
        return messageText;
    }

    public static void handleAnswer(TextMessageReceivedEvent event) {
        Boolean status = QuestProcessor.verifyAnswer(sender.getId(), messageText);
        if (status != null) {
            if (status) {
                TgMsgUtil.sendToChat(event, "Correct!");
                TgMsgUtil.sendToChat(event, QuestProcessor.incrementProgress(sender.getId()));
            } else {
                TgMsgUtil.sendToChat(event, "Incorrect! Try again.");
            }
        } else {
            TgMsgUtil.sendToChat(event, "You have already finished this quest");
        }
    }
}
