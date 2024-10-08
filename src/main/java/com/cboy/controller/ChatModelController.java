package com.cboy.controller;

import com.cboy.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class ChatModelController {

	private final ChatClient chatClient;

	@Autowired private ChatService chatService;

	ChatModelController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	@GetMapping("/ai")
	Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
		return Map.of(
				"completion",
				chatClient.prompt()
						.user(message)
						.call()
						.content());
	}

	@GetMapping("/hello")
	String hello() {
		return chatService.hello();
	}

	@GetMapping("/weather")
	String weather() {
		return chatService.getWeatherWithFunctionCalling();
	}

	@GetMapping("/role")
	String role() {
		return chatService.role();
	}

	@GetMapping("/streamRole")
	String streamRole() {
		return chatService.streamRole();
	}

	@GetMapping("/listOutputConverter")
	String listOutputConverter() {
		return chatService.listOutputConverter();
	}

	@GetMapping("/mapOutputConverter")
	String mapOutputConverter() {
		return chatService.mapOutputConverter();
	}
}
