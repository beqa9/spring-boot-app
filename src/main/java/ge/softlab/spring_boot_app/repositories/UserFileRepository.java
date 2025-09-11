package ge.softlab.spring_boot_app.repositories;

import ge.softlab.spring_boot_app.entities.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
    List<UserFile> findByUserId(Integer userId);
}
