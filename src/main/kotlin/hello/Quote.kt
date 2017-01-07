package hello

import mu.KLogging
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate

data class Value(val id: Long, val quote: String)

data class Quote(val type: String, val value: Value)

@RestController
class QuoteController(val quoteService: QuoteService) {
    companion object : KLogging()

    @GetMapping("/quote")
    fun quote(): Quote {
        logger.debug { "Requesting quote" }
        return quoteService.fetchQuote()
    }
}

@Service
@Retryable(include = arrayOf(HttpServerErrorException::class, ResourceAccessException::class))
class QuoteService(val restTemplate: RestTemplate = RestTemplate()) {
    companion object : KLogging()

    fun fetchQuote(): Quote {
        val quote = restTemplate
                .getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote::class.java)
        logger.debug { "Quote: $quote" }
        return quote
    }
}