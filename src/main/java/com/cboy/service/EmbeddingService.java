package com.cboy.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddingService {

    @Autowired
    private EmbeddingModel embeddingModel;

    public Object hello() {
        EmbeddingResponse response;
        try {
             response = embeddingModel.call(
                    new EmbeddingRequest(List.of("Hello World", "World is big and salvation is near"),
                            OllamaOptions.builder()
                                    .withModel("nomic-embed-text")
                                    .withTruncate(false)
                                    .build())
            );
        } catch (Throwable e) {
            return null;
        }
        return response;
    }
}
