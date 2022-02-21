package project.repositories;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import project.models.Gift;

public class CloudinaryRepository {

	public static String upload(MultipartFile file, Gift gift) {
		
		Map params = ObjectUtils.asMap(
		    "public_id",  LocalDateTime.now()+gift.getName(),
		    "overwrite", true,
		    "resource_type", "image",
		    "api_key", "197868216449185",
		    "api_secret","aTa2dJtFK9VQzx8S_FW2YIKY5AY",
		    "cloud_name", "duq0pz1vi"
		);
		
		Cloudinary cloudinary = new Cloudinary();
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
			return uploadResult.get("public_id").toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void delete(String url) { //en verdad utiliza el public_id
		Map config = ObjectUtils.asMap(
		  "cloud_name", "duq0pz1vi",
		  "api_key", "197868216449185",
		  "api_secret", "aTa2dJtFK9VQzx8S_FW2YIKY5AY");
		Cloudinary cloudinary = new Cloudinary(config);
		try {
			Map result = cloudinary.uploader().destroy(url,
			  ObjectUtils.asMap("resource_type","image"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
