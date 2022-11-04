package shows; // The package where this application class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 20, 2022
 */

//Including the needed imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // Allows for auto-configuration, component scan, etc.
@ComponentScan("springboot")
/**
 * This is the Spring Container instance that implements the CommandLineRunner
 * interface.
 */
public class USGameShowsApplication {

	public static void main(String[] args) {
		SpringApplication.run(USGameShowsApplication.class, args);
	}
}