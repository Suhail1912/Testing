package com.springBoot.FileUpload.Controller;

import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springBoot.FileUpload.Model.FileModel;
import com.springBoot.FileUpload.Service.FilesSevice;
import com.springBoot.FileUpload.Service.FilesSevice.UploadFileResponse;


@RestController
public class FileController {

	
	@Autowired
	private FilesSevice fileservice;
	
	@PostMapping(value="/upload")
	public ArrayList<UploadFileResponse> uploadFile(@RequestParam("fileUpload") MultipartFile[] file)
	{
		return fileservice.postMultipartFiles(file);	
	}
	
	 @GetMapping("/getFile/{filename}")
	 public ResponseEntity<Resource>  getFile(@PathVariable String filename) throws Exception
	 {
		 Resource resource=fileservice.getRequiredFile(filename);
		  return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
		
		 
	 }
	 
	 @GetMapping("/getFileById/{fileid}")
	 public ResponseEntity<Resource> getFileById(@PathVariable String fileid) throws Exception 
	 {
		 Resource resource=fileservice.getById(fileid);
		 
		 return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
		
	 }
	 
	
	
	
	
}
