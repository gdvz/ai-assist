package com.example.assist.controller;

import com.example.assist.service.VectorService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VectorController.class)
class VectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VectorService vectorService;

    @MockBean
    private VectorStore vectorStore;

    @Test
    void addDocuments_when_get_endpoint_then_should_add_data() throws Exception {

        mockMvc.perform(get("/vector/addData"))
            .andExpect(status().isOk());

        verify(vectorService, times(1)).getDocumentList();
    }

    @Test
    void searchDocuments_when_get_endpoint_then_should_return_result_as_json() throws Exception {

        String query = "test";
        String key = "news";
        String op = "ge";
        String value = "entertainment";

        List<String> expectedResult = Arrays.asList("Id:id1,Content:result1", "Id:id2,Content:result2");
        when(vectorService.getSearchResult(query, key, op, value)).thenReturn(expectedResult);

        mockMvc.perform(get("/vector/search")
                .param("query", query)
                .param("key", key)
                .param("op", op)
                .param("value", value))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("[\"Id:id1,Content:result1\",\"Id:id2,Content:result2\"]"));

        verify(vectorService, times(1)).getSearchResult(query, key, op, value);
    }

}
