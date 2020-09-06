package jacekskorupa.currencyconverter.controllers;

import jacekskorupa.currencyconverter.model.Currency;
import jacekskorupa.currencyconverter.model.RealTimeCurrency;
import jacekskorupa.currencyconverter.model.RealTimeCurrencyExchangeRate;
import jacekskorupa.currencyconverter.repositories.CurrencyRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://currencyjsapp.herokuapp.com")
@RestController
@RequestMapping({"/api"})
public class IndexController {

    CurrencyRepository currencyRepository;

    public IndexController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    @GetMapping(path = {"/getallcurrences"})
    public List<Currency> getAllCurrences(){
            List<Currency> allCurrencies= new ArrayList<>();
            currencyRepository.findAll().forEach(currency -> {
                allCurrencies.add(currency);
            });
            return allCurrencies;
    }
    @GetMapping(path = {"/getcurrencyexchangerate/{from}/exchange/{to}"})
    public String currencyExchangeRate(@PathVariable String from, @PathVariable String to){
        String currencyExchangeUrl = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=" + from + "&to_currency=" + to + "&apikey=KFT0KZZ3V5FDR44G";

        RestTemplate restTemplate = new RestTemplate();
        RealTimeCurrency exchangeRate = restTemplate.getForObject(currencyExchangeUrl, RealTimeCurrency.class);
        return exchangeRate.getRealTimeCurrencyExchangeRate().getExchangeRate();
    }

}
