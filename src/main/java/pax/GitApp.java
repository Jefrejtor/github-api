package pax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class GitApp {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 80 );
		SpringApplication.run(GitApp.class, args);

	}

}
