package com.example.assist.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class VectorServiceTest {

    @Mock
    private VectorStore vectorStore;

    @InjectMocks
    private VectorService vectorService;

    @Test
    void getDocumentList_when_execute_then_should_return_some_document_list() {

        List<Document> documentList = vectorService.getDocumentList();

        assertEquals(2225, documentList.size()); // Asserting size of the returned list\

    }

    @Test
    void getSearchResult_when_execute_then_should_return_result_string_list() {

        when(vectorStore.similaritySearch(any(SearchRequest.class))).thenReturn(getTestDocuments());

        List<String> searchResult = vectorService.getSearchResult("query", "key", "op", "value");

        assertEquals(3, searchResult.size());
    }

    private List<Document> getTestDocuments() {

        return List.of(
            new Document("content1", Map.of("news", "key1")),
            new Document("content2", Map.of("news", "key2")),
            new Document("content3", Map.of("news", "key3"))
        );
    }

}
