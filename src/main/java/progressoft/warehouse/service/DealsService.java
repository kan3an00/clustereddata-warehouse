package progressoft.warehouse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import progressoft.warehouse.WarehouseApplication;
import progressoft.warehouse.domain.Deals;
import progressoft.warehouse.model.DealsDTO;
import progressoft.warehouse.repos.DealsRepository;
import progressoft.warehouse.util.NotFoundException;


@Service
public class DealsService {

    private final DealsRepository dealsRepository;

    private static final Logger logger = LoggerFactory.getLogger(WarehouseApplication.class);

    public DealsService(final DealsRepository dealsRepository) {
        this.dealsRepository = dealsRepository;
    }

    public List<DealsDTO> findAll() {
        final List<Deals> dealsList = dealsRepository.findAll(Sort.by("id"));
        return dealsList.stream()
                .map(deals -> mapToDTO(deals, new DealsDTO()))
                .toList();
    }

    public DealsDTO get(final Long id) {
        return dealsRepository.findById(id)
                .map(deals -> mapToDTO(deals, new DealsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DealsDTO dealsDTO) {
        logger.info("create dealsDTO: {}, {}, {}", dealsDTO.getFromCurrency(), dealsDTO.getToCurrency(), dealsDTO.getAmount());
        if (dealsRepository.existsByFromCurrencyAndToCurrencyAndAmount(dealsDTO.getFromCurrency(), dealsDTO.getToCurrency(), dealsDTO.getAmount())) {
            throw new IllegalStateException("This request already exists");
        }
        final Deals deals = new Deals();
        mapToEntity(dealsDTO, deals);
        return dealsRepository.save(deals).getId();
    }

    public void update(final Long id, final DealsDTO dealsDTO) {
        final Deals deals = dealsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(dealsDTO, deals);
        dealsRepository.save(deals);
    }

    public void delete(final Long id) {
        dealsRepository.deleteById(id);
    }

    private DealsDTO mapToDTO(final Deals deals, final DealsDTO dealsDTO) {
        dealsDTO.setId(deals.getId());
        dealsDTO.setFromCurrency(deals.getFromCurrency());
        dealsDTO.setToCurrency(deals.getToCurrency());
        dealsDTO.setAmount(deals.getAmount());
        return dealsDTO;
    }

    private Deals mapToEntity(final DealsDTO dealsDTO, final Deals deals) {
        deals.setFromCurrency(dealsDTO.getFromCurrency());
        deals.setToCurrency(dealsDTO.getToCurrency());
        deals.setAmount(dealsDTO.getAmount());
        return deals;
    }

}
