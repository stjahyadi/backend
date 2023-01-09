package com.seanyoung.backend.product.internal.v1

import java.math.BigDecimal
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

data class Product(

    @field:PositiveOrZero
    val id: Long = 0,
    @field:Size(max = 100)
    val name: String,
    @field:PositiveOrZero
    val qty: Long,
    val price: BigDecimal,
    val imageUrl: String?

)
