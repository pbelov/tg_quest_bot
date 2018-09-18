package com.pbelov.java.tg.quest_bot;

import com.pbelov.java.tg.quest_bot.Utils.TgMsgUtil;
import com.pbelov.java.tg.quest_bot.Utils.Utils;
import com.pbelov.java.tg.quest_bot.quest.QuestProcessor;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

import java.util.Arrays;

@SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored", "StatementWithEmptyBody"})
class CommandsHelper extends BaseEventsHelper {
    private static final String TAG = "MessageHelper";

    private CommandsHelper() {
    }

    static String getCommandsData(CommandMessageReceivedEvent event) {
        // command params
        String[] args = event.getArgs();
        argsString = event.getArgsString();
        String command = event.getCommand();

        Utils.println(TAG, "handleCommand: args = " + Arrays.toString(args) + ", argsString = " + argsString + ", command = " + command);

        return command;
    }

    static void handleStartCommand(CommandMessageReceivedEvent event) {
        if (QuestProcessor.getProgress(sender.getId()) == 0) {
            TgMsgUtil.replyInChat(event, "Lets start the quest!");
            TgMsgUtil.sendToChat(event, QuestProcessor.getLatestQuestion(sender.getId()));
        } else {
            TgMsgUtil.sendToChat(event, "Quest is in progress already. Latest question is: \r\n" + QuestProcessor.getLatestQuestion(sender.getId()));
        }
    }

    static void handleCancelCommand(CommandMessageReceivedEvent event) {
        QuestProcessor.setProgress(sender.getId(), 0);
        TgMsgUtil.sendToChat(event, "Quest has been cancelled.");
        TgMsgUtil.sendToChat(event, QuestProcessor.getLatestQuestion(sender.getId()));
    }
}
