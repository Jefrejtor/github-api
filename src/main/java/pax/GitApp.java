package pax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitApp {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 8008 );
		SpringApplication.run(GitApp.class, args);

	}

}
