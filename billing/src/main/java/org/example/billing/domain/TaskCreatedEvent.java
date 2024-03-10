package org.example.billing.domain;

import lombok.Data;

@Data
public class TaskCreatedEvent {
    private String accountPublicId;
    private Task task;
}
