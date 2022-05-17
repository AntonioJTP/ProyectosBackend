package com.backend.EJKA1.Application;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverMessage {

    @KafkaListener(topics = "${message.topic.name:test}", groupId = "${message.group.name:test-group}")
    public void listenTopic(String message) {
        System.out.println("Received Message of topic1 in listener: " + message);
    }
}
