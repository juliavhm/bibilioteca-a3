package a3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller", "service", "model"})
public class A3Application {

	public static void main(String[] args) {
		SpringApplication.run(A3Application.class, args);
	}

}
