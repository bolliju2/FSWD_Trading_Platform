package ch.zhaw.sml.iwi.meng.leantodo.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ToDo
 */
@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private Date date;

    private double exchangePrice;
    
    private double amountCoins;

    private String symbol;

    private boolean buy;
    
    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getExchangePrice() {
        return exchangePrice;
    }

    public void setExchangePrice(double exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    public double getAmountCoins() {
        return amountCoins;
    }

    public void setAmountCoins(double amountCoins) {
        this.amountCoins = amountCoins;
    }


    @JsonIgnore
    private String owner;

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
}