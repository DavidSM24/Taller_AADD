package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8100")
@SpringBootApplication
public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AppMain.class, args);
	}

}
