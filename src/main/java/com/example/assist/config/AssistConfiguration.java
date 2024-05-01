package com.example.assist.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Configuration
public class AssistConfiguration {

    private static final Log logger = LogFactory.getLog(AssistConfiguration.class);

    private final Environment environment;

    public AssistConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean("llama3")
    @Primary
    ChatClient llama3ChatClient(OllamaApi ollamaApi) {
        //meta
        return new OllamaChatClient(ollamaApi)
            .withDefaultOptions(OllamaOptions.create().withModel("llama3").withTemperature(0.5f));
    }

    @Bean("gemma")
    ChatClient gemmaChatClientThai(OllamaApi ollamaApi) {
        //google
        return new OllamaChatClient(ollamaApi)
            .withDefaultOptions(OllamaOptions.create().withModel("gemma").withTemperature(0.5f));
    }

    @Bean("thaiopengpt")
    ChatClient thaiOpenGPTchatClient(OllamaApi ollamaApi) {
        //thai developer
            return new OllamaChatClient(ollamaApi)
            .withDefaultOptions(OllamaOptions.create().withModel("pacozaa/openthaigpt").withTemperature(0.5f));
    }

    @Bean
    String assistModelInit() throws IOException, InterruptedException {

        String url = environment.getProperty("spring.ai.ollama.base-url")+"api/generate";

        String llama3Status = processLlama3(url);
        String gemmaStatus = processGemma(url);
        String thaiOpenGPTStatus = processThaiOpenGPT(url);

        logger.info("Assist Model Initial Status"+"\n"+llama3Status+"\n"+gemmaStatus+"\n"+thaiOpenGPTStatus);

        return "Assist Model Initial Status"+"\n"+llama3Status+"\n"+gemmaStatus+"\n"+thaiOpenGPTStatus;
    }

    private String processLlama3(String url) throws InterruptedException, IOException {

        String llama3Status = "llama3 : DOWN";

        ProcessBuilder pb1 = new ProcessBuilder(
            "curl",
            "-XPOST",
            "-H", "Content-Type:application/json",
            url,
            "-d", "{\"model\":\"llama3\",\"prompt\":\"hello\"}"
        );

        Process process1 = pb1.start();

        int exitCodeProcess1 = process1.waitFor();
        String result = new BufferedReader(
            new InputStreamReader(process1.getInputStream(), StandardCharsets.UTF_8))
            .lines().collect(Collectors.joining("\n"));

        if(exitCodeProcess1 == 0 && !result.contains("not found, try pulling it first")) llama3Status = "llama3 : UP";

        return llama3Status;
    }

    private String processGemma(String url) throws InterruptedException, IOException {

        String gemmaStatus = "gemma : DOWN";

        ProcessBuilder pb2 = new ProcessBuilder(
            "curl",
            "-XPOST",
            "-H", "Content-Type:application/json",
            url,
            "-d", "{\"model\":\"gemma\",\"prompt\":\"hello\"}"
        );

        Process process2 = pb2.start();

        int exitCodeProcess2 = process2.waitFor();
        String result = new BufferedReader(
            new InputStreamReader(process2.getInputStream(), StandardCharsets.UTF_8))
            .lines().collect(Collectors.joining("\n"));

        if(exitCodeProcess2 == 0 && !result.contains("not found, try pulling it first")) gemmaStatus = "gemma : UP";

        return gemmaStatus;
    }

    private String processThaiOpenGPT(String url) throws InterruptedException, IOException {

        String thaiOpenGPTStatus = "thaiOpenGPT : DOWN";

        ProcessBuilder pb3 = new ProcessBuilder(
            "curl",
            "-XPOST",
            "-H", "Content-Type:application/json",
            url,
            "-d", "{\"model\":\"pacozaa/openthaigpt\",\"prompt\":\"hello\"}"
        );

        Process process3 = pb3.start();

        int exitCodeProcess3 = process3.waitFor();
        String result = new BufferedReader(
            new InputStreamReader(process3.getInputStream(), StandardCharsets.UTF_8))
            .lines().collect(Collectors.joining("\n"));

        if(exitCodeProcess3 == 0 && !result.contains("not found, try pulling it first")) thaiOpenGPTStatus = "thaiOpenGPT : UP";

        return thaiOpenGPTStatus;
    }

}
