package pl.sdacademy.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.sdacademy.store.components.MyPlainClass;

@SpringBootApplication
public class Main {

	@Bean
	public MyPlainClass myPlainClass() {
		return new MyPlainClass();
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
