package dev.beaver.beaverhomebudgetspring.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
public class AccountDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "owner_id",  nullable = false)
    private UserDAO owner;
    @OneToMany(mappedBy = "account")
    private List<TransactionDAO> accountTransactions;
}
