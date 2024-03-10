package org.example.billing.consumer;

import org.example.billing.domain.TaskCreatedEvent;
import org.example.billing.repository.AccountRepository;
import org.example.billing.service.TaskService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaskConsumer {
    private final TaskService taskService;
    private final AccountRepository accountRepository;

    public TaskConsumer(TaskService taskService, AccountRepository accountRepository) {
        this.taskService = taskService;
        this.accountRepository = accountRepository;
    }

    @KafkaListener(topics = {"task-stream"}, groupId = "foo")
    public void listenGroupFoo(TaskCreatedEvent taskCreatedEvent) {
        var account = accountRepository.getByPublicId(taskCreatedEvent.getAccountPublicId());
        var task = taskCreatedEvent.getTask();
        task.setAccount(account);
        taskService.save(task);

        System.out.println("Received message in group foo: " + taskCreatedEvent);
    }


}
