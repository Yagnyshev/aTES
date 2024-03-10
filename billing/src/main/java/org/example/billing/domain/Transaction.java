package org.example.billing.domain;

import lombok.Data;

@Data
public class Transaction {
    private Long id;
    private Account account;
    private String desc;
    private Integer debit;
    private Integer credit;
}
