package me.wonwoo

import me.wonwoo.domain.Beer
import me.wonwoo.domain.BeerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal
import java.math.MathContext
import java.time.Duration

@SpringBootApplication
class SpringReactiveStreamingKotlinApplication {

  @Bean
  fun init(beerRepository: BeerRepository): CommandLineRunner {
    return CommandLineRunner { _ ->
      beerRepository.deleteAll().block()
      val beers = listOf(
          Beer(name = "Heineken", price = BigDecimal(67.85, MathContext(2))),
          Beer(name = "Hoegaarden", price = BigDecimal(45.19, MathContext(2))),
          Beer(name = "Miller", price = BigDecimal(24.89, MathContext(2))),
          Beer(name = "Guinness", price = BigDecimal(84.37, MathContext(2))),
          Beer(name = "Corona", price = BigDecimal(43.93, MathContext(2))),
          Beer(name = "Budweiser", price = BigDecimal(34.75, MathContext(2)))
      )
      beerRepository.insert(beers).blockLast(Duration.ofSeconds(3))
    }
  }
}

fun main(args: Array<String>) {
  SpringApplication.run(SpringReactiveStreamingKotlinApplication::class.java, *args)
}
