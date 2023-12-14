package progressoft.warehouse.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progressoft.warehouse.model.DealsDTO;
import progressoft.warehouse.service.DealsService;


@RestController
@RequestMapping(value = "/api/deals", produces = MediaType.APPLICATION_JSON_VALUE)
public class DealsResource {

    private final DealsService dealsService;

    public DealsResource(final DealsService dealsService) {
        this.dealsService = dealsService;
    }

    @GetMapping
    public ResponseEntity<List<DealsDTO>> getAllDeals() {
        return ResponseEntity.ok(dealsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealsDTO> getDeals(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(dealsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDeals(@RequestBody @Valid final DealsDTO dealsDTO) {
        final Long createdId = dealsService.create(dealsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDeals(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final DealsDTO dealsDTO) {
        dealsService.update(id, dealsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteDeals(@PathVariable(name = "id") final Long id) {
        dealsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
