package com.dantesoft.siremono.connectors.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service("uploadService")
public interface UploadAdapter {
	void createBucket(String name);

	List<String> listBuckets() throws Exception;

	String uploadFile(String bucketName, String objectName, MultipartFile file);
	
	String uploadFromBytes(String bucketName,String objectName, String contentType, byte[] bytes);

	String uploadText(String bucketName, String objectName, String text);

	InputStream getObject(String bucketName, String objectName) throws Exception;

	void removeObject(String bucketName, String objectName) throws Exception;

	List<String> listObjectsByBycket(String bucketName) throws Exception;

	String getPresignedUrl(String bucketName, String objectName, int expirySeconds) throws Exception;

}
