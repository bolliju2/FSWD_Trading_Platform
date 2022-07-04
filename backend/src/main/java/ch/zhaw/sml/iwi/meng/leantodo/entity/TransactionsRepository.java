package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
    public List<Transactions> findByOwner(String owner);
   
    @Query("SELECT t FROM ToDo as t WHERE t.owner = ?1 AND t.archived = false")
    public List<Transactions> findAllButArchivedByOwner(String owner);
    
}