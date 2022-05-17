package com.backend.EJKA1.application;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverMessage {

    @KafkaListener(topics = "${message.topic.name:topicName}", groupId = "${message.group.name:groupId}")
    public void listenTopic(String message) {
        System.out.println("Received Message of topic1 in listener: " + message);
    }
}
