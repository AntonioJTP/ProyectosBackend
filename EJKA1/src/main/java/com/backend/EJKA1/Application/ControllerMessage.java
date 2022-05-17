package com.backend.EJKA1.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerMessage {
    @Autowired
    TransmitterMessage transmitterMessage;

    @PostMapping("/message/send/{topic}")
    public ResponseEntity<String> sendMessage(@RequestBody String message, @PathVariable String topic) {
        transmitterMessage.sendMessage(topic, message);

        return ResponseEntity.ok(message);
    }
}
