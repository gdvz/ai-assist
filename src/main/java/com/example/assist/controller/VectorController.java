package com.example.assist.controller;

import com.example.assist.service.VectorService;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/vector")
public class VectorController {

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private VectorService vectorService;

    @GetMapping("/addData")
    public void addDocuments() {

        vectorStore.add(vectorService.getDocumentList());

    }

    @GetMapping("/search")
    public List<String> searchDocuments(@RequestParam(value = "query", defaultValue = "test") String query,
                                        @RequestParam(value = "key", defaultValue = "news") String key,
                                        @RequestParam(value = "op", defaultValue = "ge") String op,
                                        @RequestParam(value = "value", defaultValue = "entertainment") String value) {


        return vectorService.getSearchResult(query,key,op,value);

    }

}


