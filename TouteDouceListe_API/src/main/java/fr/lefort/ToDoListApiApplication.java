package fr.lefort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApiApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Salut");
  }
}
