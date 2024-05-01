package com.example.assist.constant;

public class ModelRespMessage {

    public static final String LLAMA3_NOT_FOUND = "Ollama model 'llama3' not found."+"\n"+"Just install it depends on your environment."+"\n"+"  - local : ollama run llama3"+"\n"+"  - docker : docker exec -it ollama ollama run llama3";
    public static final String GEMMA_NOT_FOUND = "Ollama model 'gemma' not found."+"\n"+"Just install it depends on your environment."+"\n"+"  - local : ollama run gemma"+"\n"+"  - docker : docker exec -it ollama ollama run gemma";
    public static final String THAIOPENGPT_NOT_FOUND = "Ollama model 'thaiopengpt' not found."+"\n"+"Just install it depends on your environment."+"\n"+"  - local : ollama run pacozaa/openthaigpt"+"\n"+"  - docker : docker exec -it ollama ollama run pacozaa/openthaigpt";

}
