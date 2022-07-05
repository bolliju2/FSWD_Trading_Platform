package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.PortfolioRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Transaction;

@Component
public class PortfolioController {
    

    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<Portfolio> listAllPortfolios(String loginName){
        return portfolioRepository.findByOwner(loginName);
    }

public void persistPortfolio(Portfolio newPortfolio/*String owner */ ) {
    // We only create empty projects
    newPortfolio.setId(null);
    newPortfolio.getTransactions().clear();
    //newPortfolio.setCash(???);
    portfolioRepository.save(newPortfolio);

    
}
public void addTransaction(Long portfolioId, Transaction transaction, String owner) {
    Portfolio portfolio = portfolioRepository.getOne(portfolioId);
    if(portfolio == null || !portfolio.getOwner().equals(owner)) {
        return;
    }  
    // Ensure that JPA creates a new entity
    transaction.setTransactionId(null);
    transaction.setOwner(owner);
    portfolio.getTransactions().add(transaction);
    portfolioRepository.save(portfolio);
}
}