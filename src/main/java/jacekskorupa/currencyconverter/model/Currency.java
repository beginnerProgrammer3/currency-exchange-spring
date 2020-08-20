package jacekskorupa.currencyconverter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String intCode;
    private String name;

    public Currency(String intCode, String name) {
        this.intCode = intCode;
        this.name = name;
    }
}
