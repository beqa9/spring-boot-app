package ge.softlab.spring_boot_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user_files")
public class UserFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String objectName;

    @Column(nullable = false)
    private String bucket;

    private LocalDateTime uploadedAt;

}