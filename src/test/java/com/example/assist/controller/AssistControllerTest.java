package com.example.assist.controller;

import com.example.assist.service.AssistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssistController.class)
class AssistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssistService assistService;

    @Test
    void llama3_when_get_endpoint_then_should_return_some_text() throws Exception {
        String prompt = "Test prompt";
        String expectedResponse = "Llama3 response";

        when(assistService.llama3GenerateMessage(prompt)).thenReturn(expectedResponse);

        mockMvc.perform(get("/assist/llama3/chat")
                .param("prompt", prompt)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
            .andExpect(content().string(expectedResponse));
    }

    @Test
    void gemma_when_get_endpoint_then_should_return_some_text() throws Exception {
        String prompt = "Test prompt";
        String expectedResponse = "Gemma response";

        when(assistService.gemmaGenerateMessage(prompt)).thenReturn(expectedResponse);

        mockMvc.perform(get("/assist/gemma/chat")
                .param("prompt", prompt)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
            .andExpect(content().string(expectedResponse));
    }

    @Test
    void thaiopengpt_when_get_endpoint_then_should_return_some_text() throws Exception {
        String prompt = "Test prompt";
        String expectedResponse = "ThaiOpenGPT response";

        when(assistService.thaiOpenGPTGenerateMessage(prompt)).thenReturn(expectedResponse);

        mockMvc.perform(get("/assist/thaiOpenGpt/chat")
                .param("prompt", prompt)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
            .andExpect(content().string(expectedResponse));
    }
}
