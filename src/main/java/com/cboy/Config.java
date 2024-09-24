package com.cboy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
class Config {

    public static final String DEFAULT_OLLAMA_MODEL = "llama3.1";

    @Bean
    ChatClient chatClient() {
        return ChatClient.builder(chatModel()).build();
    }

    @Bean
    ChatModel chatModel() {
        OllamaApi ollamaApi = new OllamaApi();
        return new OllamaChatModel(ollamaApi,
                OllamaOptions.create()
                        .withModel(DEFAULT_OLLAMA_MODEL)
                        .withTemperature(0.9));
//        OpenAiApi openAiApi = new OpenAiApi("http://localhost:11434", "");
//        return new OpenAiChatModel(openAiApi,
//                OpenAiChatOptions.builder().withModel(DEFAULT_OLLAMA_MODEL).build());
    }

    @Bean
    public Function<MockWeatherService.Request, MockWeatherService.Response> getCurrentWeather() {
        return new MockWeatherService();
    }
}
