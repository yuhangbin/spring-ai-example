package com.cboy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RAGService {

    @Autowired
    ChatClient client;


}
