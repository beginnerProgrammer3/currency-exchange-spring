package jacekskorupa.currencyconverter.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jacekskorupa.currencyconverter.model.Currency;
import jacekskorupa.currencyconverter.model.RealTimeCurrency;
import jacekskorupa.currencyconverter.model.RealTimeCurrencyExchangeRate;
import jacekskorupa.currencyconverter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Map;


@RestController
@RequestMapping(path = {"/"})
public class IndexController {

    private final String currencesSource= "https://openexchangerates.org/api/currencies.json";
    private CurrencyRepository currencyRepository;


    public IndexController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @GetMapping(path = {"index"})
    public String getCurrences() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(currencesSource, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> currencesMap = objectMapper.readValue(result, objectMapper.getTypeFactory()
                .constructMapLikeType(Map.class, String.class,String.class));

        currencesMap.forEach((String key, String value) -> {
            Currency currencyToSave = new Currency(key,value);
            currencyRepository.save(currencyToSave);
            System.out.println(key + ": " + value);
        });

        return currencesMap.toString();

    }

    @GetMapping(path = {"getcurrencyexchange/{from}/convert/{to}"})
    public RealTimeCurrencyExchangeRate currencyExchangeRate(@PathVariable String from, @PathVariable String to){
        String currencyExchangeUrl = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=" + from + "&to_currency=" + to + "&apikey=KFT0KZZ3V5FDR44G";

        RestTemplate restTemplate = new RestTemplate();
        RealTimeCurrency exchangeRate = restTemplate.getForObject(currencyExchangeUrl, RealTimeCurrency.class);
        return exchangeRate.getRealTimeCurrencyExchangeRate();
    }

}
