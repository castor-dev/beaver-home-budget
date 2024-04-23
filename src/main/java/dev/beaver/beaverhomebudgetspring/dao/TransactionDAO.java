package dev.beaver.beaverhomebudgetspring.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
public class TransactionDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private BigDecimal value;
    private Boolean isCredit;
    private Boolean isPlanned;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountDAO account;
}
