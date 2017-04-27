package me.wonwoo.web

import me.wonwoo.domain.Beer
import me.wonwoo.domain.BeerRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.math.MathContext
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.stream.Stream


/**
 * Created by wonwoolee on 2017. 4. 27..
 */
@Controller
class BeerController(val beerRepository: BeerRepository) {

  val mathContext = MathContext(2)

  val random = Random()

  @GetMapping("/")
  fun index(): String = "beer"

  @GetMapping(value = "/beers", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
  @ResponseBody
  fun events(): Flux<Beer> = beerRepository.findAll()
      .flatMap { beer ->
        val eventFlux = Flux.fromStream(Stream.generate { updateBeer(beer) })
        val interval = Flux.interval(Duration.ofMillis(1500))
        Flux.zip(eventFlux, interval).map { it.t1 }
      }.share()
      .log()

  private fun updateBeer(beer: Beer) = beer.copy(
      price = beer.price?.add(beer.price?.multiply(
          BigDecimal(0.5 * random.nextDouble()), mathContext)),
      instant = Instant.now()
  )
}