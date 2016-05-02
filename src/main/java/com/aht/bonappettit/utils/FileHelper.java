package com.aht.bonappettit.utils;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Component;

@Component
public class FileHelper {
	public void sayHello() {
		System.out.println("Hola mundo we!");
	}
	
	public boolean createFile(InputStream inputStream, String directory) {
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream out = new FileOutputStream(new File(directory));
			out = new FileOutputStream(new File(directory));
			while ((read = inputStream.read(bytes)) != -1)
				out.write(bytes, 0, read);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) { return false; }
	}

	public String hashFunction(String filename, String dishName) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String date = dateFormat.format(new Date());
		String name = filename + dishName + date; 
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(name.getBytes(), 0, name.length());
			String hashed = new BigInteger(1, md.digest()).toString(64);
			return hashed;
		} catch(Exception exception) {
			return null;
		}
	}
}
