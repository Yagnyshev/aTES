package org.example.billing.service;

import org.example.billing.domain.Task;
import org.example.billing.domain.Transaction;
import org.example.billing.repository.AccountRepository;
import org.example.billing.repository.TaskRepository;
import org.example.billing.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final IntStream cost;
    private final IntStream reward;

    public TaskService(TaskRepository taskRepository, TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.taskRepository = taskRepository;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        var random = new Random();
        cost = random.ints(10, 20);
        reward =  random.ints(20, 40);
    }

    public void save(Task task) {
        task.setCost(cost.findFirst().getAsInt());
        task.setReward(reward.findFirst().getAsInt());
        taskRepository.save(task);

        var transaction = new Transaction();
        transaction.setAccount(task.getAccount());
        transaction.setCredit(task.getReward());
        transaction.setDesc("Ассайн задачи " + task.getName());
        transactionRepository.save(transaction);
    }
}
