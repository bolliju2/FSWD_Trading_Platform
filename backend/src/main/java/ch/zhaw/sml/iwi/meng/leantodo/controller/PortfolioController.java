package ch.zhaw.sml.iwi.meng.leantodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.PortfolioRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class PortfolioController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio getPortfolio(String loginName){
        return userRepository.findById(loginName).get().getPortfolio();
    }

    //Hier Start kapital setzen
    public void persistPortfolio(Portfolio newPortfolio, String owner ) {
        
        // We only create empty portfolios
        newPortfolio.setId(null);
        //newPortfolio.setCash(1000000.0);
        newPortfolio.getTransactions().clear();
        portfolioRepository.save(newPortfolio);
    }

    

    //Mit dieser Methode Cash updaten
    // public void updatePortfolio(Portfolio portfolio, String owner) {
    //     Portfolio orig = portfolioRepository.getOne(portfolio.getId());
    //     if(orig == null || !orig.getOwner().equals(owner)) {
    //         return;
    //     }
    //     portfolio.setOwner(owner);
    //     portfolioRepository.save(portfolio);
    // }
}