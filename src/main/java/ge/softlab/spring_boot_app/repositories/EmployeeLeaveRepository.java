package ge.softlab.spring_boot_app.repositories;

import ge.softlab.spring_boot_app.entities.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Integer> {
    List<EmployeeLeave> findByEmployeeId(Integer employeeId);
}