package ge.softlab.spring_boot_app.repositories;

import ge.softlab.spring_boot_app.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {



}