package hello

import mu.KLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    companion object : KLogging()

    @Bean
    fun init(repository: CustomerRepository) = CommandLineRunner {
        // save a couple of customers
        repository.save(Customer("Jack", "Bauer"))
        repository.save(Customer("Chloe", "O'Brian"))
        repository.save(Customer("Kim", "Bauer"))
        repository.save(Customer("David", "Palmer"))
        repository.save(Customer("Michelle", "Dessler"))

        // fetch all customers
        logger.info("Customers found with findAll():")
        logger.info("-------------------------------")
        for (customer in repository.findAll()) {
            logger.info(customer.toString())
        }
        logger.info("")

        // fetch an individual customer by ID
        val customer = repository.findOne(1L)
        logger.info("Customer found with findOne(1L):")
        logger.info("--------------------------------")
        logger.info(customer.toString())
        logger.info("")

        // fetch customers by last name
        logger.info("Customer found with findByLastName('Bauer'):")
        logger.info("--------------------------------------------")
        for (bauer in repository.findByLastName("Bauer")) {
            logger.info(bauer.toString())
        }
        logger.info("")
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
