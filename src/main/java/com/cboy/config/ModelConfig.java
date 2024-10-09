package com.cboy.config;

import com.cboy.service.MockWeatherService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
class ModelConfig {

    @Bean
    ChatClient chatClient() {
        return ChatClient.builder(chatModel()).build();
    }

    @Bean
    ChatModel chatModel() {
        OllamaApi ollamaApi = new OllamaApi();
        return new OllamaChatModel(ollamaApi,
                OllamaOptions.create()
                        .withModel(OllamaModel.LLAMA3_1.id())
                        .withTemperature(0.9));
//        OpenAiApi openAiApi = new OpenAiApi("http://localhost:11434", "");
//        return new OpenAiChatModel(openAiApi,
//                OpenAiChatOptions.builder().withModel(DEFAULT_OLLAMA_MODEL).build());
    }

    @Bean
    EmbeddingModel embeddingModel() {
        OllamaApi ollamaApi = new OllamaApi();
        return new OllamaEmbeddingModel(ollamaApi,
                OllamaOptions.builder()
//                        .withModel(OllamaModel.LLAMA3_1.id())
                        .withModel("all-minilm")
                        .build());
    }

    @Bean
    public Function<MockWeatherService.Request, MockWeatherService.Response> getCurrentWeather() {
        return new MockWeatherService();
    }
}
