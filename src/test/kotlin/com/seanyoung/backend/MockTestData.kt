package com.seanyoung.backend

import com.seanyoung.backend.product.internal.ProductEntity
import com.seanyoung.backend.product.internal.v1.Product
import java.math.BigDecimal
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Random
import java.util.UUID
import java.util.concurrent.atomic.AtomicLong

@Suppress("LongParameterList")
object MockTestData {

    fun product(
        id: Long = IdGenerator.randomLong(),
        name: String = "product $id name",
        qty: Long = IdGenerator.randomLong(),
        price: BigDecimal = IdGenerator.randomBigDecimal(),
        imageUrl: String? = "/any/"
    ): Product {
        return Product(
            id = id,
            name = name,
            qty = qty,
            price = price,
            imageUrl = imageUrl
        )
    }
    fun productEntity(
        id: Long = IdGenerator.randomLong(),
        name: String = "Product $id name",
        qty: Long = IdGenerator.randomLong(),
        price: BigDecimal = IdGenerator.randomBigDecimal(),
        imageUrl: String? = "/any/",
        creationTimestamp: Instant = Instant.now().truncatedTo(ChronoUnit.MICROS),
        modificationTimestamp: Instant? = null
    ): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            qty = qty,
            price = price,
            imageUrl = imageUrl,
            creationTimestamp = creationTimestamp,
            modificationTimestamp = modificationTimestamp
        )
    }
}

object IdGenerator {
    private const val MIN = 100000000
    private const val MAX = 999999999
    private val random = Random()
    private val actionId = AtomicLong(122333)

    fun nextActionId(): String {
        actionId.incrementAndGet()
        return actionId.toString()
    }

    fun randomInt(): Int {
        return random.nextInt(MAX - MIN) + MIN
    }

    fun randomIntToString(): String {
        return randomInt().toString()
    }

    fun randomUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBigDecimal(): BigDecimal {
        return BigDecimal(Math.random())
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }
}
