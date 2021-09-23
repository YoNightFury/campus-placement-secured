package com.app;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {

	public static final String STORAGE_PATH = System.getProperty("user.home") + "\\.temp_project";
	public static final String RESUME_PATH = STORAGE_PATH+"\\resume";
	public static final String PHOTO_PATH = STORAGE_PATH+"\\photo";
	
	@Override
	public void run(String... args) throws Exception {
		// create the folder if does not exists
		File resume_folder = new File(RESUME_PATH);
		File photo_folder = new File(PHOTO_PATH);
		resume_folder.mkdirs();
		photo_folder.mkdirs();
//		if (!Files.notExists(Paths.get(STORAGE_PATH)))
//			resume_folder.mkdir();
		

	}

}
