package jacekskorupa.currencyconverter.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import jacekskorupa.currencyconverter.model.Currency;
import jacekskorupa.currencyconverter.repositories.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class LoadCurrences implements CommandLineRunner {

    private final String currencesSource= "https://openexchangerates.org/api/currencies.json";
    private CurrencyRepository currencyRepository;

    public LoadCurrences(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(currencesSource, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,String> currencesMap = objectMapper.readValue(result, objectMapper.getTypeFactory()
                    .constructMapLikeType(Map.class, String.class,String.class));

            currencesMap.forEach((String key, String value) -> {
                Currency currencyToSave = new Currency(key,value);
                currencyRepository.save(currencyToSave);
            });

    }
}
