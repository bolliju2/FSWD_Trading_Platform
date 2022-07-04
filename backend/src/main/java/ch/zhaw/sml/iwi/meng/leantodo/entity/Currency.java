package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Currency {

    private static final String COMMA_DELIMITER = null;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String symbol;
    
    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    public List<List<String>> getData() {
        
        return null;
    }

    
    
    

    
    // Methode um Grafik darzustellen



    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    
    
}