package com.example.assist.service;

import groovyjarjarantlr4.v4.runtime.misc.MultiMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.assist.constant.VectorFilterOperator.getByValue;

@Service
public class VectorService {

    private static final Log logger = LogFactory.getLog(VectorService.class);

    @Autowired
    private VectorStore vectorStore;

    public List<Document> getDocumentList() {

        List<Document> documentList = new ArrayList<>();

        getDataMap().forEach((key,value) -> value.forEach(wording ->  documentList.add(new Document(wording, Map.of("news", key)))));

        return documentList;

    }

    public MultiMap<String,String> getDataMap() {

        MultiMap<String,String> dataByType = new MultiMap<>();

        //example data is bbc_data.csv
        try (BufferedReader br = new BufferedReader(new FileReader("bbc_data.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] values = new String[1];
                String dataType = "";

                if(line.contains(",entertainment")) {
                    values = line.split(",entertainment");
                    dataType = "entertainment";
                } else if(line.contains(",business")) {
                    values = line.split(",business");
                    dataType = "business";
                } else if(line.contains(",politics")) {
                    values = line.split(",politics");
                    dataType = "politics";
                } else if(line.contains(",sport")) {
                    values = line.split(",sport");
                    dataType = "sport";
                } else if(line.contains(",tech")) {
                    values = line.split(",tech");
                    dataType = "tech";
                }

                if(!dataType.isBlank()) dataByType.map(dataType,values[0]);
            }

        } catch (Exception e) {
            logger.error("getDataMap : read file error");
        }

        logger.info("getDataMap size : "+dataByType.size());
        return dataByType;

    }

    public List<String> getSearchResult(String query,String key,String op,String value) {

        List<Document> results = vectorStore.similaritySearch(SearchRequest.defaults()
            .withQuery(query)
            .withSimilarityThreshold(0.5)
            .withFilterExpression(" "+key+" "+getByValue(op).getValue()+" '"+value+"'")
            .withTopK(5));

        logger.info("getSearchResult size : "+results.size());
        return results.stream().map(doc -> "Id: " + doc.getId() + ", Content: " + doc.getContent()).toList();

    }

}
