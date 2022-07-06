package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ch.zhaw.sml.iwi.meng.leantodo.currencyData.CurrencyData;
import ch.zhaw.sml.iwi.meng.leantodo.currencyData.TimeSeries;

@RestController
public class CurrencyEndpoint {

    @RequestMapping(path = "/api/currency", method = RequestMethod.GET)
    public List<String> getCurrency() {
        List<String> res = new ArrayList<>();
        res.add("BTC");
        res.add("ETH");
        res.add("DOGE");
        res.add("OG");
        return res;
    }



    @RequestMapping(path = "/api/currency/{name}", method = RequestMethod.GET)
    public TimeSeries getCurrency(@PathVariable(name = "name") String name) {

        //Check if the name in path is max 4 letters long to reduce injection
        if (!name.matches("[A-Z]+") || name.length() > 4) {
            System.out.println("We don't allow injection!!!");
            return null;
        }

        // Create a new object of TimeSeries and pass the name of the currency
        TimeSeries ts = new TimeSeries(name);

        // Read the .csv-file from resources/currencyData with the given name
        try (BufferedReader br = new BufferedReader(
                new FileReader("backend/src/main/resources/currencyData/" + name + ".csv"))) {
            String line;
            // Check if the line is not null
            while ((line = br.readLine()) != null) {
                // Split the line by comma and save the values
                String[] values = line.split(",");
                if (values.length >= 2) {
                    // Parse the unix timestamp to "long" and save it to a variable
                    long timestamp = (long) Double.parseDouble(values[0]) * 1000;
                    // Parse the exchange rate to a "double" and save it to a variable
                    double value = Double.parseDouble(values[1]);
                    //Methode to create historical values with the date and value of currency
                    ts.getHistoricalValues().add(
                            new CurrencyData(
                                    new Date(timestamp),
                                    value));
                }
            }
            //EVTL. LETZTER WERT VON historicalValue (value)
            
        } catch (Exception e) {
           // e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ts;
    }

}
