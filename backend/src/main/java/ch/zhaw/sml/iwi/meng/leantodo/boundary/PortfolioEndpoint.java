package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.PortfolioController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;

@RestController
public class PortfolioEndpoint {
    
    @Autowired
    private PortfolioController portfolioController;

    @RequestMapping(path = "/api/portfolio", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public Portfolio getPortfolios(Principal principal) {
        return portfolioController.getPortfolio(principal.getName());
    }
}
