package jacekskorupa.currencyconverter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jacekskorupa.currencyconverter.model.Currency;
import jacekskorupa.currencyconverter.model.CurrencyChange;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class CurrencyExchangeController {

    @GetMapping(path = {"timeseriesweekly/{from}/and/{to}"})
    public String getWeeklyExchangeChanges(@PathVariable String from, @PathVariable String to) throws JsonProcessingException {
        String weeklyCurrencyExchange = "https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol=" + from + "&to_symbol=" + to + "&apikey=KFT0KZZ3V5FDR44G";

        RestTemplate restTemplate = new RestTemplate();
        String exchangeRateString = restTemplate.getForObject(weeklyCurrencyExchange, String.class);

        JSONObject obj = new JSONObject(exchangeRateString);
        String pageName = obj.getJSONObject("Time Series FX (Weekly)").toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, CurrencyChange> currencesMap = objectMapper.readValue(pageName, objectMapper.getTypeFactory()
                .constructMapLikeType(Map.class, String.class,CurrencyChange.class));

        currencesMap.forEach((String key, CurrencyChange currencyChange) -> {
            System.out.println(key+ "  opened Price:" + currencyChange.getOpen() + " closed Price" +
                    currencyChange.getClose() + " low: " + currencyChange.getLow() + " high: "+ currencyChange.getHigh());
        });
        return  pageName;
    }

    @GetMapping(path = {"timeseriesdaily/{from}/and/{to}"})
    public String getDailyExchangeChanges(@PathVariable String from, @PathVariable String to) throws JsonProcessingException {
        String dailyCurrencyExchange = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=" + from + "&to_symbol=" + to + "&apikey=KFT0KZZ3V5FDR44G";

        RestTemplate restTemplate = new RestTemplate();
        String exchangeRateString = restTemplate.getForObject(dailyCurrencyExchange, String.class);

        JSONObject obj = new JSONObject(exchangeRateString);
        String pageName = obj.getJSONObject("Time Series FX (Daily)").toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, CurrencyChange> currencesMap = objectMapper.readValue(pageName, objectMapper.getTypeFactory()
                .constructMapLikeType(Map.class, String.class,CurrencyChange.class));

        currencesMap.forEach((String key, CurrencyChange currencyChange) -> {
            System.out.println(key+ "  opened Price:" + currencyChange.getOpen() + " closed Price" +
                    currencyChange.getClose() + " low: " + currencyChange.getLow() + " high: "+ currencyChange.getHigh());
        });
        return  pageName;
    }

    @GetMapping(path = {"timeseriesmonthly/{from}/and/{to}"})
    public String getMonthlyExchangeChanges(@PathVariable String from, @PathVariable String to) throws JsonProcessingException {
        String monthlyCurrencyExchange = "https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=" + from + "&to_symbol=" + to + "&apikey=KFT0KZZ3V5FDR44G";

        RestTemplate restTemplate = new RestTemplate();
        String exchangeRateString = restTemplate.getForObject(monthlyCurrencyExchange, String.class);

        JSONObject obj = new JSONObject(exchangeRateString);
        String pageName = obj.getJSONObject("Time Series FX (Monthly)").toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, CurrencyChange> currencesMap = objectMapper.readValue(pageName, objectMapper.getTypeFactory()
                .constructMapLikeType(Map.class, String.class,CurrencyChange.class));

        currencesMap.forEach((String key, CurrencyChange currencyChange) -> {
            System.out.println(key+ "  opened Price:" + currencyChange.getOpen() + " closed Price" +
                    currencyChange.getClose() + " low: " + currencyChange.getLow() + " high: "+ currencyChange.getHigh());
        });
        return  pageName;
    }
}
