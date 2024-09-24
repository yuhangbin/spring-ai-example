package com.cboy.service;

import com.cboy.pojo.ActorFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ChatModel chatModel;


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

        var promptOptions = OllamaOptions.builder()
                .withFunctionCallbacks(List.of(FunctionCallbackWrapper.builder(new MockWeatherService())
                        .withName("getCurrentWeather")
                        .withDescription("Get the weather in location")
                        .withResponseConverter((response) -> "" + response.temp() + response.unit())
                        .build()
                ))
                .build();
        ChatResponse response = chatModel.call(new Prompt(messages, promptOptions));
        return response.toString();
    }

    public String role() {
        UserMessage userMessage = new UserMessage(
                "Tell me about 3 famous pirates from the Golden Age of Piracy and what they did."
        );
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Bob", "voice", "pirate"));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        ChatResponse response = chatModel.call(prompt);
        return response.toString();
    }

    public String streamRole() {
        UserMessage userMessage = new UserMessage(
                "Tell me about 3 famous pirates from the Golden Age of Piracy and what they did."
        );
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Bob", "voice", "pirate"));
        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        Flux<ChatResponse> flux = chatModel.stream(prompt);
        List<ChatResponse> responses = flux.collectList().block();
        assert responses != null;
        return responses.stream()
                .map(ChatResponse::getResults)
                .flatMap(List::stream)
                .map(Generation::getOutput)
                .map(AssistantMessage::getContent)
                .collect(Collectors.joining());
    }

    public String listOutputConverter() {
        DefaultConversionService conversionService = new DefaultConversionService();
        ListOutputConverter outputConverter = new ListOutputConverter(conversionService);

        String format = outputConverter.getFormat();
        String template = """
                List five {subject}
                {format}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template,
                Map.of("subject", "ice cream flavors", "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = this.chatModel.call(prompt).getResult();
        List<String> list = outputConverter.convert(generation.getOutput().getContent());
        assert list != null;
        return list.toString();
    }

    public String mapOutputConverter() {
        MapOutputConverter outputConverter = new MapOutputConverter();

        String format = outputConverter.getFormat();
        String template = """
				Provide me a List of {subject}
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template,
                Map.of("subject", "numbers from 1 to 9 under they key name 'numbers'", "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = chatModel.call(prompt).getResult();
        Map<String,Object> result = outputConverter.convert(generation.getOutput().getContent());
        assert result != null;
        return result.toString();
    }
}
