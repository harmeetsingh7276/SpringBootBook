package com.springboot.api.book.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	//public final String UPLOAD_DIR = "/Users/harmeetsinghgorowada/Documents/workspace-springboot/SpringBootRestAPIBook/src/main/resources/static/image";
	//so if we use the class ClassPathResource we will get path till ==>/Users/harmeetsinghgorowada/Documents/workspace-springboot/SpringBootRestAPIBook/src/main/resources/
	public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	public FileUploadHelper() throws IOException{
		
	}
	public boolean uploadFile(MultipartFile file) {
		boolean f = false;
		try {
//			InputStream is = file.getInputStream();
//			byte data[] = new byte[is.available()];
//			is.read(data);
//
//			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + File.separator + file.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			f = true;
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}