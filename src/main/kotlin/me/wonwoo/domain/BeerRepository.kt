package me.wonwoo.domain

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

/**
 * Created by wonwoolee on 2017. 4. 27..
 */
interface BeerRepository : ReactiveMongoRepository<Beer, String>