package jacekskorupa.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "1. open",
        "2. high",
        "3. low",
        "4. close"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyChange {

    @JsonProperty("1. open")
    public String open;
    @JsonProperty("2. high")
    public String high;
    @JsonProperty("3. low")
    public String low;
    @JsonProperty("4. close")
    public String close;


}
