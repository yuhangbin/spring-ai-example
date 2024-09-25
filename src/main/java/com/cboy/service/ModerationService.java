package com.cboy.service;

import org.springframework.stereotype.Service;

@Service
public class ModerationService {

    public void moderation() {
        // OpenAiModerationApi openAiModerationApi = new OpenAiModerationApi(System.getenv("OPENAI_API_KEY"));
        //
        //OpenAiModerationModel openAiModerationModel = new OpenAiModerationModel(openAiModerationApi);
        //
        //OpenAiModerationOptions moderationOptions = OpenAiModerationOptions.builder()
        //    .withModel("text-moderation-latest")
        //    .build();
        //
        //ModerationPrompt moderationPrompt = new ModerationPrompt("Text to be moderated", moderationOptions);
        //ModerationResponse response = openAiModerationModel.call(moderationPrompt);
    }
}
