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

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createDeals(@RequestBody @Valid final DealsDTO dealsDTO) {
        try {
            final Long createdId = dealsService.create(dealsDTO);
            return new ResponseEntity<>(createdId, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Log the error if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
