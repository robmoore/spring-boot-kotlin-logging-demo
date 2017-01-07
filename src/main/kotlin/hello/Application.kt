package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableRetry
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
