package com.cboy.service;

import org.springframework.stereotype.Service;

@Service
public class AudioService {

    // TODO api key
    public void transcription() {
        // var openAiAudioApi = new OpenAiAudioApi(System.getenv("OPENAI_API_KEY"));
        //
        //var openAiAudioTranscriptionModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);
        //
        //var transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
        //    .withResponseFormat(TranscriptResponseFormat.TEXT)
        //    .withTemperature(0f)
        //    .build();
        //
        //var audioFile = new FileSystemResource("/path/to/your/resource/speech/jfk.flac");
        //
        //AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
        //AudioTranscriptionResponse response = openAiTranscriptionModel.call(transcriptionRequest);
    }

    public void tts() {
        // var openAiAudioApi = new OpenAiAudioApi(System.getenv("OPENAI_API_KEY"));
        //
        //var openAiAudioSpeechModel = new OpenAiAudioSpeechModel(openAiAudioApi);
        //
        //var speechOptions = OpenAiAudioSpeechOptions.builder()
        //    .withResponseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
        //    .withSpeed(1.0f)
        //    .withModel(OpenAiAudioApi.TtsModel.TTS_1.value)
        //    .build();
        //
        //var speechPrompt = new SpeechPrompt("Hello, this is a text-to-speech example.", speechOptions);
        //SpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);
        //
        //// Accessing metadata (rate limit info)
        //OpenAiAudioSpeechResponseMetadata metadata = response.getMetadata();
        //
        //byte[] responseAsBytes = response.getResult().getOutput();
    }
}
