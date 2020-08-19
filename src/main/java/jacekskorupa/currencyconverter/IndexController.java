package jacekskorupa.currencyconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping(path = {"/index"})
public class IndexController {

    private final String currenciesSource= "https://openexchangerates.org/api/currencies.json";


    @GetMapping
    public String getCurrences() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(currenciesSource, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> currencesMap = objectMapper.readValue(result, objectMapper.getTypeFactory()
                .constructMapLikeType(Map.class, String.class,String.class));

        currencesMap.forEach((String key, String value) -> System.out.println(key + ": " + value));

        return result;

    }
}
