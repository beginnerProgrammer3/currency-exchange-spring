package jacekskorupa.currencyconverter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import jacekskorupa.currencyconverter.model.CurrencyChange;
import jacekskorupa.currencyconverter.model.CurrencyChangeToList;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CurrencyExchangeController {

    private final String API_KEY = "KFT0KZZ3V5FDR44G";

    @GetMapping(path = {"/api/charts/weekly/{from}/and/{to}"})
    public List<CurrencyChangeToList> getWeeklyExchangeChanges(@PathVariable String from, @PathVariable String to) throws JsonProcessingException {
        String weeklyCurrencyExchange = "https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol=" + from + "&to_symbol=" + to + "&apikey=" + API_KEY;

        return getListOfPeroidTimeCurrencyExchange(weeklyCurrencyExchange,"Weekly");
    }

    @GetMapping(path = {"/api/charts/daily/{from}/and/{to}"})
    public List<CurrencyChangeToList> getDailyExchangeChanges(@PathVariable String from, @PathVariable String to) throws JsonProcessingException {
        String dailyCurrencyExchange = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=" + from + "&to_symbol=" + to + "&apikey=" + API_KEY;

        return getListOfPeroidTimeCurrencyExchange(dailyCurrencyExchange,"Daily");
    }

    @GetMapping(path = {"/api/charts/monthly/{from}/and/{to}"})
    public List<CurrencyChangeToList> getMonthlyExchangeChanges(@PathVariable String from, @PathVariable String to) throws JsonProcessingException {
        String monthlyCurrencyExchange = "https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=" + from + "&to_symbol=" + to + "&apikey=" + API_KEY;

        return getListOfPeroidTimeCurrencyExchange(monthlyCurrencyExchange,"Monthly");
    }

    private List<CurrencyChangeToList> getListOfPeroidTimeCurrencyExchange(String timeExchangeUrl, String peroid) throws JsonProcessingException {
    RestTemplate restTemplate = new RestTemplate();
    String exchangeRateString = restTemplate.getForObject(timeExchangeUrl, String.class);

    JSONObject obj = new JSONObject(exchangeRateString);
    String pageName = obj.getJSONObject("Time Series FX ("+peroid+")").toString();

    ObjectMapper objectMapper = new ObjectMapper();
    TreeMap<String, CurrencyChange> currencesMap = objectMapper.readValue(pageName, objectMapper.getTypeFactory()
            .constructMapLikeType(TreeMap.class, String.class,CurrencyChange.class));
    List<CurrencyChangeToList> listToShow = new ArrayList<>();
        currencesMap.forEach((String key, CurrencyChange currencyChange) -> {

                CurrencyChangeToList currencyChangeToList = new CurrencyChangeToList(key, currencyChange.getOpen(), currencyChange.getHigh(), currencyChange.getLow(), currencyChange.getClose());
                listToShow.add(currencyChangeToList);
                });
        return listToShow;
    }}