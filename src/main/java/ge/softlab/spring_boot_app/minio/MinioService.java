package ge.softlab.spring_boot_app.minio;


import ge.softlab.spring_boot_app.entities.UserFile;
import ge.softlab.spring_boot_app.repositories.UserFileRepository;
import io.minio.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final MinioProperties props;
    private final UserFileRepository fileRepo;

    public MinioService(MinioClient minioClient, MinioProperties props, UserFileRepository fileRepo) {
        this.minioClient = minioClient;
        this.props = props;
        this.fileRepo = fileRepo;
    }

    private void createBucketIfNotExists() throws Exception {
        boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(props.getBucket()).build()
        );
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(props.getBucket()).build());
        }
    }

    public String uploadUserFile(MultipartFile file, Integer userId) throws Exception {
        createBucketIfNotExists();

        String objectName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(props.getBucket())
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        UserFile log = new UserFile();
        log.setUserId(userId);
        log.setFileName(file.getOriginalFilename());
        log.setObjectName(objectName);
        log.setBucket(props.getBucket());
        log.setUploadedAt(LocalDateTime.now());
        fileRepo.save(log);

        return objectName;
    }


    public InputStream downloadUserFile(String objectName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(props.getBucket())
                        .object(objectName)
                        .build()
        );
    }
}
