package com.cboy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    private static final String DEFAULT_OLLAMA_MODEL = "llama3.1";

    @Bean
    ChatClient chatClient() {
        OpenAiApi openAiApi = new OpenAiApi("http://localhost:11434", "");
        OpenAiChatModel openAiChatModel = new OpenAiChatModel(openAiApi,
                OpenAiChatOptions.builder().withModel(DEFAULT_OLLAMA_MODEL).build());
        return ChatClient.builder(openAiChatModel).build();
    }

}
