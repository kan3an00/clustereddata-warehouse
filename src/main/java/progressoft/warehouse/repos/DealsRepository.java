package progressoft.warehouse.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import progressoft.warehouse.domain.Deals;
import java.math.BigDecimal;


public interface DealsRepository extends JpaRepository<Deals, Long> {
    boolean existsByFromCurrencyAndToCurrencyAndAmount(String fromCurrency, String toCurrency, BigDecimal amount);
}
