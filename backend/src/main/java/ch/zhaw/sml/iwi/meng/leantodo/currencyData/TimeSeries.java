package ch.zhaw.sml.iwi.meng.leantodo.currencyData;

import java.util.ArrayList;
import java.util.List;

//Class for the Time Series for the specific Currency
public class TimeSeries {
    private final List<CurrencyData> historicalValues = new ArrayList<>();

    private final String symbol;

    public TimeSeries(String symbol) {
        this.symbol = symbol;
    }

    public List<CurrencyData> getHistoricalValues() {
        return historicalValues;
    }

    public String getSymbol() {
        return symbol;
    }
    

}
