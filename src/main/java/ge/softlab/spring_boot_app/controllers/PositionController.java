package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.Position;
import ge.softlab.spring_boot_app.models.PositionModel;
import ge.softlab.spring_boot_app.services.PositionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
@Tag(name = "position-controller", description = "crud operations")
public class PositionController {

    private final PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Position> getAllPositions() {
        return service.getAllPositions();
    }

    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Integer id) {
        return service.getPositionById(id);
    }

    @PostMapping
    public Position addPosition(@RequestBody PositionModel model) {
        return service.addPosition(model);
    }

    @PutMapping("/{id}/update")
    public Position updatePosition(@PathVariable Integer id, @RequestBody PositionModel model) {
        return service.updatePosition(id, model);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePosition(@PathVariable Integer id) {
        service.deletePosition(id);
        return ResponseEntity.ok("Position with ID " + id + " deleted successfully.");
    }
}
