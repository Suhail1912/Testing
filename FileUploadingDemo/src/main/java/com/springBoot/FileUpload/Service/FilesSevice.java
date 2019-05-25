package com.springBoot.FileUpload.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.springBoot.FileUpload.Model.FileModel;
import com.springBoot.FileUpload.Repository.fileRepository;

@Service
public class FilesSevice {

	@Autowired
	fileRepository filerepository;
	
	@Autowired
	ConfigFileLocationProperties filepath;
	
	
	public Resource getRequiredFile(String filename) throws Exception
	{
		FileModel f=filerepository.getByFileName(filename);
		System.out.println(f.getFilename());
		Path file= Paths.get(filepath.getUploadDir()).resolve(f.getFilename());
		 Resource resource=new UrlResource(file.toUri());
		 if(resource.exists())
		 {
			 return resource;
		 }
		 else
		 {
			 throw new Exception();
		 }
	}
	
	public Resource getById(String id) throws Exception
	{
		FileModel f=filerepository.getByFileId(id);
		System.out.println(f.getFilename());
		Path file= Paths.get(filepath.getUploadDir()).resolve(f.getFilename());
		 Resource resource=new UrlResource(file.toUri());
		 if(resource.exists())
		 {
			 return resource;
		 }
		 else
		 {
			 throw new Exception();
		 }
	}
	
	public ArrayList<UploadFileResponse> postMultipartFiles(MultipartFile[] file) {
		// TODO Auto-generated method stub
		ArrayList<UploadFileResponse> l=new ArrayList<UploadFileResponse>();
		 try {
//				For Single File Upload
//         byte[] bytes = file.getBytes();
//         Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//         //Files.write(path, bytes); U can use Files.write() method with bytes array or Files.copy() method. Nothing much difference
//         Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//         String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                 .path("/downloads/")
//                 .path(file.getOriginalFilename())
//                 .toUriString();
//        UploadFileResponse e= new UploadFileResponse(file.getOriginalFilename(), fileDownloadUri,
//                 file.getContentType(), file.getSize());
		 
		 
		 	//For Single or MultiFile Upload
		 System.out.println(file.length);
		 int i=0;
		 	for(MultipartFile f:file)
		 	{
		 		System.out.println("here...................");
		 		byte[] bytes=f.getBytes();
		 		Path p=Paths.get(filepath.getUploadDir() + f.getOriginalFilename());
		 		Files.write(p, bytes);
		 		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                    .path("/getFile/")
	                    .path(f.getOriginalFilename())
	                    .toUriString();
		 		 l.add( new UploadFileResponse(f.getOriginalFilename(), fileDownloadUri,f.getContentType(), f.getSize()));;
		 		 
		 		 FileModel fm=new FileModel(f.getOriginalFilename(),f.getContentType(),f.getSize());
		 		 filerepository.save(fm);
		           
		 	}	 	
		 	return l;
     } catch (IOException e1) {
         e1.printStackTrace();
     }
		return l;
	}
	
	public class UploadFileResponse {
	    private String fileName;
	    private String fileDownloadUri;
	    private String fileType;
	    private long size;

	    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
	        this.fileName = fileName;
	        this.fileDownloadUri = fileDownloadUri;
	        this.fileType = fileType;
	        this.size = size;
	    }

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileDownloadUri() {
			return fileDownloadUri;
		}

		public void setFileDownloadUri(String fileDownloadUri) {
			this.fileDownloadUri = fileDownloadUri;
		}

		public String getFileType() {
			return fileType;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}

		
	}
}
