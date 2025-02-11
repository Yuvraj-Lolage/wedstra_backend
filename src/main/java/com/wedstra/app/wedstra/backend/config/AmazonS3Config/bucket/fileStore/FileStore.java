package com.wedstra.app.wedstra.backend.config.AmazonS3Config.bucket.fileStore;

import com.wedstra.app.wedstra.backend.config.AmazonS3Config.bucket.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final S3Client s3;

    @Autowired
    public FileStore(S3Client s3) {
        this.s3 = s3;
    }

    public String save(String path, String fileName, Optional<Map<String, String>> optionalMetadata, InputStream inputStream) {
        try {
            // Build the PutObjectRequest with metadata if provided
            PutObjectRequest.Builder putObjectRequestBuilder = PutObjectRequest.builder()
                    .bucket(BucketName.PROFILE_IMAGE.getBucketName())
                    .key(path + "/" + fileName);

            // Add user metadata if present
            optionalMetadata.ifPresent(metadataMap -> putObjectRequestBuilder.metadata(metadataMap));

            // Upload the file
            PutObjectResponse response = s3.putObject(
                    putObjectRequestBuilder.build(),
                    RequestBody.fromInputStream(inputStream, inputStream.available())
            );

            // Return the ETag for confirmation
            return "File uploaded successfully with ETag: " + response.eTag();

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
}
