package com.cboy.controller;

import com.cboy.service.VectorStoreService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/vector")
@RestController
public class VectorStoreController {

    @Autowired
    VectorStoreService vectorStoreService;

    @GetMapping("/topK/{k}")
    public List<String> getTopK(@PathVariable("k") int k) {
        vectorStoreService.add();
        return vectorStoreService.topK(k).stream().map(Document::getContent).collect(Collectors.toList());
    }
}
