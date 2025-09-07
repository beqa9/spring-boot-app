package ge.softlab.spring_boot_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(schema = "public", name = "employees")
@Where(clause = "is_deleted = false")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(optional = false)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(name = "hired_date")
    private LocalDate hiredDate = LocalDate.now();

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
