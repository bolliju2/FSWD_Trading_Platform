package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.PortfolioRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Transaction;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TransactionsRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class TransactionController {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<Transaction> listAllTransactions(String loginName) {
        Portfolio portfolio = userRepository.findById(loginName).get().getPortfolio();
        

        return portfolio.getTransactions();
    }

    // public void persistTransaction(Transaction newTransaction, String owner) {
        
    // }

    public void addTransaction(Transaction transaction, String owner) {
        
        transaction.setOwner(owner);
        transaction.setDate(new Date());
        //transaction.setExchangePrice(20.0); //Verbinden mit eingelesenen Daten
        //transaction.setAmountCoins(2); //Dynamisch anpassen
        transaction.setTransactionId(null);
        transactionsRepository.save(transaction);
        
        
        
        
        // Portfolio portfolio = userRepository.findById(owner).get().getPortfolio();
        
        // // Ensure that JPA creates a new entity
        // transaction.setTransactionId(null);
        // transaction.setOwner(owner);
        // portfolio.getTransactions().add(transaction);
        // portfolioRepository.save(portfolio);
    }


    
}
