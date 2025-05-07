package com.dantesoft.siremono.connectors.upload;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MinioStorageUploader implements UploadAdapter {

  private final MinioClient minioClient;

  @Override
  public void createBucket(String name) {
    try {
      var oldBucket = BucketExistsArgs.builder().bucket(name).build();
      boolean found = minioClient.bucketExists(oldBucket);
      if (!found) {
        var newBucket = MakeBucketArgs.builder().bucket(name).build();
        minioClient.makeBucket(newBucket);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error al crear bucket: " + name, e);
    }
  }

  @Override
  public List<String> listBuckets() throws Exception {
    List<String> bucketNames = new ArrayList<>();
    List<Bucket> buckets = minioClient.listBuckets();
    for (Bucket bucket : buckets) {
      bucketNames.add(bucket.name());
    }
    return bucketNames;
  }

  @Override
  public String uploadFile(String bucketName, String objectName, MultipartFile file) {
    try {
      createBucket(bucketName);

      InputStream inputStream = new ByteArrayInputStream(file.getBytes());
      minioClient.putObject(PutObjectArgs.builder()
          .bucket(bucketName)
          .object(objectName)
          .stream(inputStream, file.getSize(), -1)
          .contentType(file.getContentType())
          .build());

      return objectName;
    } catch (Exception e) {
      throw new RuntimeException("Error al subir archivo: " + objectName, e);
    }
  }

  @Override
  public String uploadFromBytes(String bucketName, String objectName, String contentType, byte[] bytes) {
    try {
      createBucket(bucketName);
      InputStream inputStream = new ByteArrayInputStream(bytes);
      var obj = PutObjectArgs.builder()
          .bucket(bucketName)
          .object(objectName)
          .stream(inputStream, bytes.length, -1)
          .contentType(contentType)
          .build();
      minioClient.putObject(obj);

      return objectName;
    } catch (Exception e) {
      throw new RuntimeException("Error al subir archivo a " + bucketName, e);
    }
  }

  @Override
  public String uploadText(String bucketName, String objectName, String text) {
    try {
      createBucket(bucketName);

      byte[] textBytes = text.getBytes();
      InputStream inputStream = new ByteArrayInputStream(textBytes);
      minioClient.putObject(PutObjectArgs.builder()
          .bucket(bucketName)
          .object(objectName)
          .stream(inputStream, textBytes.length, -1)
          .contentType("text/plain")
          .build());

      return objectName;
    } catch (Exception e) {
      throw new RuntimeException("Error al subir texto como archivo: " + objectName, e);
    }
  }

  @Override
  public InputStream getObject(String bucketName, String objectName) throws Exception {
    return minioClient
        .getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
  }

  @Override
  public void removeObject(String bucketName, String objectName) throws Exception {
    minioClient
        .removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
  }

  @Override
  public List<String> listObjectsByBycket(String bucketName) throws Exception {
    List<String> objectNames = new ArrayList<>();
    Iterable<Result<Item>> results =
        minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());

    for (Result<Item> result : results) {
      Item item = result.get();
      objectNames.add(item.objectName());
    }

    return objectNames;
  }

  @Override
  public String getPresignedUrl(String bucketName, String objectName, int expirySeconds) throws Exception {
    return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
        .method(Method.GET)
        .bucket(bucketName)
        .object(objectName)
        .expiry(expirySeconds)
        .build());
  }


}
