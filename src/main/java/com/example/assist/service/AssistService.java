package com.example.assist.service;

import com.example.assist.constant.ModelRespMessage;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AssistService {

    @Autowired
    @Qualifier("llama3")
    private ChatClient llama3Chat;

    @Autowired
    @Qualifier("gemma")
    private ChatClient gemmaChat;

    @Autowired
    @Qualifier("thaiopengpt")
    private ChatClient thaiOpenGptChat;

    public String llama3GenerateMessage(String promptMessage) {

        String chatResponse = "";

        try {
            chatResponse = llama3Chat.call(promptMessage);
        } catch (Exception ex) {
            chatResponse = ModelRespMessage.LLAMA3_NOT_FOUND;
        }

        return chatResponse;
    }

    public String gemmaGenerateMessage(String promptMessage) {

        String chatResponse = "";

        try {
            chatResponse = gemmaChat.call(promptMessage);
        } catch (Exception ex) {
            chatResponse = ModelRespMessage.GEMMA_NOT_FOUND;
        }

        return chatResponse;
    }

    public String thaiOpenGPTGenerateMessage(String promptMessage) {

        String chatResponse = "";

        try {
            chatResponse = thaiOpenGptChat.call(promptMessage);
        } catch (Exception ex) {
            chatResponse = ModelRespMessage.THAIOPENGPT_NOT_FOUND;
        }

        return chatResponse;
    }
}
