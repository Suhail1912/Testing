package com.springBoot.FileUpload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.springBoot.FileUpload.Service.ConfigFileLocationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	ConfigFileLocationProperties.class
})
public class FileUploadingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadingDemoApplication.class, args);
	}

}
