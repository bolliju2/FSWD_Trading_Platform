package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.text.SimpleDateFormat;
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

    public List<Transaction> listAllTransactions(String loginName) {

        Portfolio portfolio = userRepository.findById(loginName).get().getPortfolio();
        return portfolio.getTransactions();
    }

    public void addTransaction(Transaction transaction, String owner) {

        //Calculate the total of value of the executed transaction
        Double total = transaction.getExchangePrice() * transaction.getAmountCoins();
        Portfolio portfolio = userRepository.findById(owner).get().getPortfolio();
        
        //Check if the user buys or sells an amount of coin
        if(transaction.isBuy()){
            //Subtract the total of currency value from the cash in portfolio
            portfolio.setCash(portfolio.getCash() - (Math.round(total * 100.0) / 100.0));
        }else{
            //Add the total of currency value to the cash in portfolio
            portfolio.setCash(portfolio.getCash() + (Math.round(total * 100.0) / 100.0));
        }

        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.YYYY hh:mm:ss");  
        String strDate= formatter.format(date);  
        transaction.setOwner(owner);
        transaction.setDate(strDate);
        portfolioRepository.save(portfolio);
        addTransactionToList(transaction, owner);
        transactionsRepository.save(transaction);
    }

    public void addTransactionToList(Transaction transaction, String owner){

        Portfolio portfolio = userRepository.findById(owner).get().getPortfolio();
        portfolio.setTransactions(transaction);
    }


    
}
