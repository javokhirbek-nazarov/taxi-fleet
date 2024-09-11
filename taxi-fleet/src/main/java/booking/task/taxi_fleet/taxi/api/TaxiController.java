package booking.task.taxi_fleet.taxi.api;

import booking.task.taxi_fleet.taxi.model.TaxiDto;
import booking.task.taxi_fleet.taxi.model.ChangeStatusDto;
import booking.task.taxi_fleet.taxi.service.TaxiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = TaxiController.PATH)
@RequiredArgsConstructor
public class TaxiController {

    public static final String PATH = "/taxis";

    private final TaxiService taxiService;

    @GetMapping
    public ResponseEntity<List<TaxiDto>> getAll() {
        return ResponseEntity.ok(taxiService.getAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaxiDto> changeStatus(@PathVariable Long id,
        @RequestBody ChangeStatusDto body) {
        return ResponseEntity.ok(taxiService.changeStatus(id, body.getStatus()));
    }


}
