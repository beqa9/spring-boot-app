package ge.softlab.spring_boot_app.repositories;

import ge.softlab.spring_boot_app.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByIsDeletedFalse();
}