package com.cboy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.cboy.Config.DEFAULT_OLLAMA_MODEL;

@Service
public class ChatService {

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private OpenAiChatModel openAiChatModel;


    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;

    public String hello() {
        List<ActorFilms> actorFilms = chatClient.prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {
                });
        return actorFilms.toString();
    }

    public String getWeatherWithFunctionCalling() {

        UserMessage userMessage = new UserMessage(
                "What's the weather like in San Francisco, Tokyo, and Paris? Return the temperature in Celsius.");

        List<Message> messages = new ArrayList<>(List.of(userMessage));

        var promptOptions = OpenAiChatOptions.builder()
                .withFunctionCallbacks(List.of(FunctionCallbackWrapper.builder(new MockWeatherService())
                        .withName("getCurrentWeather")
                        .withDescription("Get the weather in location")
                        .withResponseConverter((response) -> "" + response.temp() + response.unit())
                        .build()
                ))
                .build();
        ChatResponse response = openAiChatModel.call(new Prompt(messages, promptOptions));
        return response.toString();
    }

    public String role() {
        UserMessage userMessage = new UserMessage(
                "Tell me about 3 famous pirates from the Golden Age of Piracy and what they did."
        );
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Bob", "voice", "pirate"));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        ChatResponse response = openAiChatModel.call(prompt);
        return response.toString();
    }
}
