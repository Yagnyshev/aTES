package org.example.task.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = {"accounts-stream"}, groupId = "foo")
    public void listenGroupFoo(String messages) {
        System.out.println("Received message in group foo: " + messages);
    }
}
