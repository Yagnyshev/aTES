package org.example.billing.domain;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String publicId;
    private Account account;
    private String name;
    private Integer cost;
    private Integer reward;
}
