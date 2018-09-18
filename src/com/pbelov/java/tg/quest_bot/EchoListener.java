package com.pbelov.java.tg.quest_bot;

import com.pbelov.java.tg.quest_bot.quest.QuestProcessor;
import pro.zackpollard.telegrambot.api.event.Listener;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.event.chat.message.PhotoMessageReceivedEvent;
import pro.zackpollard.telegrambot.api.event.chat.message.TextMessageReceivedEvent;

import java.io.IOException;

@SuppressWarnings({"FieldCanBeLocal", "unchecked", "ResultOfMethodCallIgnored"})
public class EchoListener implements Listener {
    private final String TAG = "EchoListener";

    public void EchoListener() {
        QuestProcessor.restoreProgress();
    }

    @Override
    public void onCommandMessageReceived(CommandMessageReceivedEvent event) {
        BaseEventsHelper.getBaseData(event, "command");
        handleCommand(event);
    }

    @Override
    public void onTextMessageReceived(TextMessageReceivedEvent event) {
        BaseEventsHelper.getBaseData(event, "text message");
        handleMessage(event);
    }

    @Override
    public void onPhotoMessageReceived(PhotoMessageReceivedEvent event) {

    }

    private void handleCommand(CommandMessageReceivedEvent event) {
        final String command = CommandsHelper.getCommandsData(event);

        final String COMMAND_START = "start";
        final String COMMAND_CANCEL = "cancel";

        if (command.equals(COMMAND_START)) {
            CommandsHelper.handleStartCommand(event);
        } else if (command.equals(COMMAND_CANCEL)) {
            CommandsHelper.handleCancelCommand(event);
        }
    }

    private void handleMessage(TextMessageReceivedEvent event) {
        String messageText = MessageHelper.getMessagesData(event);

        MessageHelper.handleAnswer(event);
    }
}
