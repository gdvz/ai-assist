package com.example.assist.controller;

import com.example.assist.service.AssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/assist")
public class AssistController {

    @Autowired
    private AssistService assistService;

    @GetMapping("llama3/chat")
    public ResponseEntity<String> llama3(@RequestParam(value = "prompt", defaultValue = "Who are you?") String prompt) {
        String aiResponse = assistService.llama3GenerateMessage(prompt);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

    @GetMapping("gemma/chat")
    public ResponseEntity<String> gemma(@RequestParam(value = "prompt", defaultValue = "Who are you?") String prompt) {
        String aiResponse = assistService.gemmaGenerateMessage(prompt);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

    @GetMapping("thaiOpenGpt/chat")
    public ResponseEntity<String> thaiOpenGpt(@RequestParam(value = "prompt", defaultValue = "Who are you?") String prompt) {
        String aiResponse = assistService.thaiOpenGPTGenerateMessage(prompt);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

}
