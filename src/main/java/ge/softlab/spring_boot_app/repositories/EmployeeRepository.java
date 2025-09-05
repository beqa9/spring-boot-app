package ge.softlab.spring_boot_app.repositories;

import ge.softlab.spring_boot_app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
