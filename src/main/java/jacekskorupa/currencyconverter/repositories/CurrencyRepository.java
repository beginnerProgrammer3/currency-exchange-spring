package jacekskorupa.currencyconverter.repositories;

import jacekskorupa.currencyconverter.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
