package org.example.task.service;

import org.example.task.domen.Task;
import org.example.task.repository.AccountRepository;
import org.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;

    public TaskService(TaskRepository taskRepository, AccountRepository accountRepository) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
    }

    public void add(Task task) {
        var account = accountRepository.getRandomAccount();
        task.setAccount(account);
        task.setPublicTaskId(UUID.randomUUID().toString());
        taskRepository.save(task);
    }

    public void complete(Long id) {
        var task = taskRepository.getReferenceById(id);
        task.setStatus(Task.Status.DONE);
        taskRepository.save(task);
    }

    public void shuffle() {
        taskRepository.shuffle();
    }

    public List<Task> get() {
        return taskRepository.findAll();
    }
}
