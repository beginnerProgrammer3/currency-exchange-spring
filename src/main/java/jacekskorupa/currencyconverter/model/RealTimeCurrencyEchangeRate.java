package jacekskorupa.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "1. From_Currency Code",
        "2. From_Currency Name",
        "3. To_Currency Code",
        "4. To_Currency Name",
        "5. Exchange Rate",
        "6. Last Refreshed",
        "7. Time Zone",
        "8. Bid Price",
        "9. Ask Price"
})
public class RealTimeCurrencyEchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("1. From_Currency Code")
    private String FromCurrencyCode;
    @JsonProperty("2. From_Currency Name")
    private String FromCurrencyName;
    @JsonProperty("3. To_Currency Code")
    private String ToCurrencyCode;
    @JsonProperty("4. To_Currency Name")
    private String ToCurrencyName;
    @JsonProperty("5. Exchange Rate")
    private String ExchangeRate;
    @JsonProperty("6. Last Refreshed")
    private String LastRefreshed;
    @JsonProperty("7. Time Zone")
    private String TimeZone;
    @JsonProperty("8. Bid Price")
    private String BidPrice;
    @JsonProperty("9. Ask Price")
    private String AskPrice;

}
