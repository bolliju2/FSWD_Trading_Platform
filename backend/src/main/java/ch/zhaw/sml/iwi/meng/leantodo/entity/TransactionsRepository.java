package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction,Long> {
    public ArrayList<Transaction> findByOwner(String owner);
}