package ge.softlab.spring_boot_app.controllers;

import ge.softlab.spring_boot_app.entities.UserFile;
import ge.softlab.spring_boot_app.minio.MinioService;
import ge.softlab.spring_boot_app.repositories.UserFileRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/files")
@Tag(name = "User Files", description = "Upload and download user files")
public class UserFileController {

    private final MinioService minioService;
    private final UserFileRepository fileRepo;

    public UserFileController(MinioService minioService, UserFileRepository fileRepo) {
        this.minioService = minioService;
        this.fileRepo = fileRepo;
    }

    @PostMapping("/upload")
    public String uploadFile(@PathVariable Integer userId,
                             @RequestParam("file") MultipartFile file) throws Exception {
        return minioService.uploadUserFile(file, userId);
    }

    @GetMapping
    public List<UserFile> listFiles(@PathVariable Integer userId) {
        return fileRepo.findByUserId(userId);
    }

    @GetMapping("/download/{objectName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer userId,
                                               @PathVariable String objectName) throws Exception {
        InputStream stream = minioService.downloadUserFile(objectName);
        byte[] content = stream.readAllBytes();
        stream.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + objectName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }
}