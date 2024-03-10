package org.example.task.domen;

import jakarta.persistence.*;

@Entity
public final class Task {
    @Id
    @Column(name = "task_id")
    private Long id;
    private String publicTaskId;
    private String description;
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "task_account",
            joinColumns =
                    { @JoinColumn(name = "task_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "account_id") })
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicTaskId() {
        return publicTaskId;
    }

    public void setPublicTaskId(String publicTaskId) {
        this.publicTaskId = publicTaskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public enum Status {
        IN_PROGRESS, DONE;
    }
}
