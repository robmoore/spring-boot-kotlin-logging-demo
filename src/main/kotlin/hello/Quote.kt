package hello

import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

data class Value(val id: Long, val quote: String)

data class Quote(val type: String, val value: Value)

@RestController
class QuoteController {
    companion object : KLogging()

    @GetMapping("/quote")
    fun fetchQuote(restTemplate: RestTemplate): Quote {
        val quote = restTemplate
                .getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote::class.java)
        logger.debug { "Quote: $quote" }
        return quote
    }
}
