package vikavl.cloud.computing.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "vikavl.cloud.computing.inventory")
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(CustomerRepository repository) {
//		return (args) -> {
//			// save a few customers
//			repository.save(new Customer("Jack", "Bauer"));
//			repository.save(new Customer("Chloe", "O'Brian"));
//			repository.save(new Customer("Kim", "Bauer"));
//			repository.save(new Customer("David", "Palmer"));
//			repository.save(new Customer("Michelle", "Dessler"));
//
//			// fetch all customers
//			logger.info("Customers found with findAll():");
//			logger.info("-------------------------------");
//			repository.findAll().forEach(customer -> {
//				logger.info(customer.toString());
//			});
//			logger.info("");
//
//			// fetch an individual customer by ID
//			Customer customer = repository.findById(1L);
//			logger.info("Customer found with findById(1L):");
//			logger.info("--------------------------------");
//			logger.info(customer.toString());
//			logger.info("");
//
//			// fetch customers by last name
//			logger.info("Customer found with findByLastName('Bauer'):");
//			logger.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				logger.info(bauer.toString());
//			});
//			logger.info("");
//		};
//	}

}
