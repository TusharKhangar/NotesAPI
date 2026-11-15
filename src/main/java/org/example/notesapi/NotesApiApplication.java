package org.example.notesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApiApplication {

	public static void main(String[] args) {
//        System.out.println("DB URL: " + System.getenv("DB_URL"));
//        System.out.println("DB USER: " + System.getenv("DB_USERNAME"));
//        String password = System.getenv("DB_PASSWORD");
//
//        System.out.println("Password matches: " +
//                "secret".equals(password));
		SpringApplication.run(NotesApiApplication.class, args);
	}

}
