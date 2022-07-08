package ch.zhaw.sml.iwi.meng.leantodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@Component
public class PortfolioController {
    
    @Autowired
    private UserRepository userRepository;

    public Portfolio getPortfolio(String loginName){
        return userRepository.findById(loginName).get().getPortfolio();
    }
}