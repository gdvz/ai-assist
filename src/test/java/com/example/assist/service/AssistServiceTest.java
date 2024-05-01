package com.example.assist.service;

import com.example.assist.constant.ModelRespMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.ChatClient;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssistServiceTest {

    @Mock
    private ChatClient llama3Chat;

    @Mock
    private ChatClient gemmaChat;

    @Mock
    private ChatClient thaiOpenGptChat;

    @InjectMocks
    private AssistService assistService;

    @Test
    void llama3GenerateMessage_when_chat_to_llama3_and_model_available_then_should_response_some_text() {
        String promptMessage = "Test message";
        String expectedResponse = "Llama3 response";

        when(llama3Chat.call(promptMessage)).thenReturn(expectedResponse);

        String actualResponse = assistService.llama3GenerateMessage(promptMessage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void gemmaGenerateMessage_when_chat_to_gemma_and_model_available_then_should_response_some_text() {
        String promptMessage = "Test message";
        String expectedResponse = "Gemma response";

        when(gemmaChat.call(promptMessage)).thenReturn(expectedResponse);

        String actualResponse = assistService.gemmaGenerateMessage(promptMessage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void thaiOpenGPTGenerateMessage_when_chat_to_thaiopengpt_and_model_available_then_should_response_some_text() {
        String promptMessage = "Test message";
        String expectedResponse = "ThaiOpenGPT response";

        when(thaiOpenGptChat.call(promptMessage)).thenReturn(expectedResponse);

        String actualResponse = assistService.thaiOpenGPTGenerateMessage(promptMessage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void llama3GenerateMessage_when_chat_to_llama3_and_model_unavailable_then_should_response_error() {
        String promptMessage = "Test message";
        String expectedResponse = ModelRespMessage.LLAMA3_NOT_FOUND;

        when(llama3Chat.call(promptMessage)).thenThrow(new RuntimeException());

        String actualResponse = assistService.llama3GenerateMessage(promptMessage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void gemmaGenerateMessage_when_chat_to_gemma_and_model_unavailable_then_should_response_error() {
        String promptMessage = "Test message";
        String expectedResponse = ModelRespMessage.GEMMA_NOT_FOUND;

        when(gemmaChat.call(promptMessage)).thenThrow(new RuntimeException());

        String actualResponse = assistService.gemmaGenerateMessage(promptMessage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void thaiOpenGPTGenerateMessage_when_chat_to_thaiopengpt_and_model_unavailable_then_should_response_error() {
        String promptMessage = "Test message";
        String expectedResponse = ModelRespMessage.THAIOPENGPT_NOT_FOUND;

        when(thaiOpenGptChat.call(promptMessage)).thenThrow(new RuntimeException());

        String actualResponse = assistService.thaiOpenGPTGenerateMessage(promptMessage);

        assertEquals(expectedResponse, actualResponse);
    }
}
