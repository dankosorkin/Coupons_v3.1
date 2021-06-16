package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import core.services.FileStorageService;

@RestController
@CrossOrigin
public class FileController {

	@Autowired
	private FileStorageService service;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam MultipartFile file) {
		return this.service.storeFile(file);
	}

}
