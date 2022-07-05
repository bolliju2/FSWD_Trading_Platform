package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.zhaw.sml.iwi.meng.leantodo.controller.PortfolioController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Transaction;

public class PortfolioEndpoint {
    
    
    @Autowired
    private PortfolioController portfolioController;

    @RequestMapping(path = "/api/portfolio", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    //Brauchen wir nicht denkt Besfort
    public List<Portfolio> getPortfolios(Principal principal) {
        return portfolioController.listAllPortfolios(principal.getName());
    }
    
    @RequestMapping(path = "/api/portfolio/{id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void addTransaction(@RequestParam(name="id") 
    Long portfolioId, @RequestBody Transaction transaction,  Principal principal) {
        portfolioController.addTransaction(portfolioId, transaction, principal.getName());
    }
}
