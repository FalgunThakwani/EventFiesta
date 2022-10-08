package group11.EventFiesta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventFiestaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventFiestaApplication.class, args);

		System.out.println("Hello world");

		getAppName();
	}

	public static String getAppName() {
		return "Event Fiesta";
	}

}
