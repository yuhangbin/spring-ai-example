package com.cboy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
class Config {

    public static final String DEFAULT_OLLAMA_MODEL = "llama3.1";

    @Bean
    ChatClient chatClient() {
        return ChatClient.builder(openAiChatModel()).build();
    }

    @Bean
    OpenAiChatModel openAiChatModel() {
        OpenAiApi openAiApi = new OpenAiApi("http://localhost:11434", "");
        return new OpenAiChatModel(openAiApi,
                OpenAiChatOptions.builder().withModel(DEFAULT_OLLAMA_MODEL).build());
    }

    @Bean
    public Function<MockWeatherService.Request, MockWeatherService.Response> getCurrentWeather() {
        return new MockWeatherService();
    }
}
