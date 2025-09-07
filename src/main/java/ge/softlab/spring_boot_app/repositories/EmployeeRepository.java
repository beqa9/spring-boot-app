package ge.softlab.spring_boot_app.repositories;

import ge.softlab.spring_boot_app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByIsDeletedFalse();


}
