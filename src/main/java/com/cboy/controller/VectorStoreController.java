package com.cboy.controller;

import com.cboy.service.VectorStoreService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/vector")
@RestController
public class VectorStoreController {

    @Autowired
    VectorStoreService vectorStoreService;

    @GetMapping("/topK/{k}")
    public List<Document> getTopK(@PathVariable("k") int k) {
        vectorStoreService.add();
        return vectorStoreService.topK(k);
    }
}
