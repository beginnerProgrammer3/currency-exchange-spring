package jacekskorupa.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Realtime Currency Exchange Rate"
})
public class RealTimeCurrency {
    @JsonProperty("Realtime Currency Exchange Rate")
    private RealTimeCurrencyExchangeRate realTimeCurrencyExchangeRate;


}
