package jacekskorupa.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyChangeToList {

    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
}
