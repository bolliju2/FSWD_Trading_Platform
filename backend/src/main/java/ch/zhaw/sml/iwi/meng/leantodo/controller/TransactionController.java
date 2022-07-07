package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.PortfolioRepository;
//import ch.zhaw.sml.iwi.meng.leantodo.entity.PortfolioRepository;
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

    //Get all Transactions from Portfolio with the logged user
    public List<Transaction> listAllTransactions(String loginName) {

        Portfolio portfolio = userRepository.findById(loginName).get().getPortfolio();
        

        return portfolio.getTransactions();
    }


    //HIER MIT VARIABLEN ARBEITEN
    public void addTransaction(Transaction transaction, String owner) {
        
        transaction.setOwner(owner); //set the owner
        transaction.setDate(new Date()); //create a new Date (current date)
        // transaction.setExchangePrice(20.0); //Verbinden mit eingelesenen Daten
        // transaction.setAmountCoins(2); //Dynamisch anpassen

        Double total = transaction.getExchangePrice() * transaction.getAmountCoins();
        Portfolio portfolio = userRepository.findById(owner).get().getPortfolio();
        portfolio.setCash(portfolio.getCash() - (Math.round(total * 100.0) / 100.0));

        portfolioRepository.save(portfolio);
        
        addTransactionToList(transaction, owner);
        transactionsRepository.save(transaction);
    }

    public void addTransactionToList(Transaction transaction, String owner){

        Portfolio portfolio = userRepository.findById(owner).get().getPortfolio();
        portfolio.setTransactions(transaction);
        // List<Transaction> transList = transactionsRepository.findByOwner(owner);
        // portfolio.setTransactions(transList);

    }


    
}
