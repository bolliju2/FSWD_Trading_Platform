package ch.zhaw.sml.iwi.meng.leantodo.currencyData;

import java.util.Date;


public class CurrencyData {
    private final Date timestamp;
    private final Double value;


    public CurrencyData(Date timestamp, Double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public Double getValue() {
        return value;
    }
    
    
    @Override
    public String toString() {
        return timestamp.toString() + " # " + value;
    }

    
}
