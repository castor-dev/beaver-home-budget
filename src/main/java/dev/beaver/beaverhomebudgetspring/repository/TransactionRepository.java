package dev.beaver.beaverhomebudgetspring.repository;

import dev.beaver.beaverhomebudgetspring.dao.TransactionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDAO, UUID> {

}
