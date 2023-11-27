package ru.sarmosov.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sarmosov.gpt.service.GptService;

@Component
@Log4j
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    @Autowired
    private GptService service;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        if (message.equals("/start")) {
            sendMessage(chatId, "При использовании бота нужно отправить запрос одним сообщением." +
                    "Следующий запрос можно отправить только после получения ответа, " +
                    "иначе сервер ляжет. Поддержка многопоточности будет добавлена позже");
            return;
        }
        log.debug(update.getMessage().getChat().getFirstName());
        sendMessage(chatId, "Ответ генерируется...");
        sendMessage(chatId, service.gptAnswer(message));
    }


    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
