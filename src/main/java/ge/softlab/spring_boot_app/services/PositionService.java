package ge.softlab.spring_boot_app.services;

import ge.softlab.spring_boot_app.entities.Position;
import ge.softlab.spring_boot_app.models.PositionModel;

import java.util.List;

public interface PositionService {
    List<Position> getAllPositions();
    Position getPositionById(Integer id);
    Position addPosition(PositionModel model);
    Position updatePosition(Integer id, PositionModel model);
    void deletePosition(Integer id);
}