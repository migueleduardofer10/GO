package com.example.apitransfer240931.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="owner_name", length = 60, nullable = false)
    private String ownerName;
    @Column(name="credit_card_number", length = 16, nullable = false)
    private String creditCardNumber;
    @Column(name="balance", nullable = false)
    private BigDecimal balance;
    @Column(name="currency", length = 3, nullable = false)
    private String currency;
}
