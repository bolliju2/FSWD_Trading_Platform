package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Transaction;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TransactionsRepository;

@Component
public class TransactionController {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transaction> listAllToDos(String loginName) {
        return transactionsRepository.findAllButArchivedByOwner(loginName);
    }

    public void persistTransaction(Transaction newTransaction, String owner) {
        newTransaction.setOwner(owner);
        //newToDo.setId(null);
        transactionsRepository.save(newTransaction);
    }

   // public void updateToDo(Transaction toDo, String owner) {
      /*  Transaction orig = toDoRepository.getOne(toDo.getId());
        // Check if the original ToDo was present and that it belonged to the same owner
        if(orig == null || !orig.getOwner().equals(owner)) {
            return;
        }
        // Ok, let's overwrite the existing toDo.
        toDo.setOwner(owner); // Set the owner because this property is ignored in the Rest API
        toDoRepository.save(toDo);*/
    //}
    
}
