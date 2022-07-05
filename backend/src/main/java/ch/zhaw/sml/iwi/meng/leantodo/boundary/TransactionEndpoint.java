package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.TransactionController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Transaction;


@RestController
public class TransactionEndpoint {

    @Autowired
    private TransactionController transactionController;

    @RequestMapping(path = "/api/transaction", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Transaction> transaction(Principal principal) {
        return  transactionController.listAllTransactions(principal.getName());        
    }

    @RequestMapping(path = "/api/transaction", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void addTransaction( @RequestBody Transaction transaction,  Principal principal) {
        transactionController.addTransaction(transaction, principal.getName());
    }
    
    // @RequestMapping(path = "/api/todo", method = RequestMethod.PUT)
    // @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    // public void updateToDo(@RequestBody ToDo toDo, Principal principal) {
    //     toDoController.updateToDo(toDo, principal.getName());
    // }
}