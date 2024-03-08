package org.example.task.web;

import org.example.task.domen.Task;
import org.example.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/tasks")
    public List<Task> getTasks() {
        return taskService.get();
    }

    @PostMapping(value = "/tasks")
    public ResponseEntity<?> addTask(Task task) {
        taskService.add(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PatchMapping(value = "/tasks/{id}")
    public void completeTask(@PathVariable Long id) {
        taskService.complete(id);
    }
    
    @PatchMapping(value = "/tasks")
    public void shuffleTasks() {
        taskService.shuffle();
    }
}
