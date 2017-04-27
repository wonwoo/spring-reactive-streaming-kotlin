package me.wonwoo.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

/**
 * Created by wonwoolee on 2017. 4. 27..
 */
@Document
data class Beer(
    @Id
    var id: String? = null,
    var name: String? = null,
    var price: BigDecimal? = null,
    var instant: Instant = Instant.now()
)