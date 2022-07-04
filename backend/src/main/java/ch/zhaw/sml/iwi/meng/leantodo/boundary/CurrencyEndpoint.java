package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.currencyData.CurrencyData;
import ch.zhaw.sml.iwi.meng.leantodo.currencyData.TimeSeries;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Currency;

@RestController
public class CurrencyEndpoint {
    
    @RequestMapping(path = "/api/currency/{name}", method = RequestMethod.GET)
    public TimeSeries getCurrency(@PathVariable(name = "name") String name) {

        if(!name.matches("[A-Z]+") || name.length() > 4) {
            System.out.println("We don't allow injection!!!");
            return null;
        }

        TimeSeries ts = new TimeSeries(name);

        try (BufferedReader br = new BufferedReader(new FileReader("backend/src/main/resources/currencyData/"+name+".csv"))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length >= 2) {
                    long timestamp = (long)Double.parseDouble(values[0]) * 1000;
                    double value = Double.parseDouble(values[1]);
                ts.getHistoricalValues().add(
                    new CurrencyData(
                        new Date(timestamp), 
                        value
                    ));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }
    
}
