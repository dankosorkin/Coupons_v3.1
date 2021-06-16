package core.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	@Value("${file.upload-dir}")
	private String downloadsDir;
	private Path fileStoragePath;

	@PostConstruct
	private void init() {
		this.fileStoragePath = Paths.get(downloadsDir).toAbsolutePath().normalize();

		try {
			Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
			throw new RuntimeException("could not create upload directory", e);
		}

	}

	/**
	 * The method store a file to server
	 */
	public String storeFile(MultipartFile file) {
		// normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// check for invalid characters
		if (fileName.contains("..")) {
			throw new RuntimeException("filename contains invalid path sequence " + fileName);
		}

		// copy file to the target location (replacing existing file with the same name)
		try {
			Path targetLocation = this.fileStoragePath.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException("storing file " + fileName + " failed", e);
		}

	}

	/**
	 * The method load a file from server
	 */
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStoragePath.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("file " + fileName + " not found");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("file " + fileName + " not found", e);
		}

	}

}
