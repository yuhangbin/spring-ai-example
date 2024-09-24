package com.cboy.controller;

import com.cboy.service.EmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/embedding")
@RestController
public class EmbeddingModelController {

    @Autowired
    EmbeddingService embeddingService;

    @GetMapping("/hello")
    public Object hello() {
        return embeddingService.hello();
    }
}
