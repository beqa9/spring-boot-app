package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Position;
import ge.softlab.spring_boot_app.models.PositionModel;
import ge.softlab.spring_boot_app.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository repository;

    public PositionServiceImpl(PositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Position> getAllPositions() {
        return repository.findAll();
    }

    @Override
    public Position getPositionById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Position addPosition(PositionModel model) {
        Position position = new Position();
        position.setName(model.name());
        return repository.save(position);
    }

    @Override
    public Position updatePosition(Integer id, PositionModel model) {
        Position existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(model.name());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void deletePosition(Integer id) {
        repository.deleteById(id);
    }
}