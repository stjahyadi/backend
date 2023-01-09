package com.seanyoung.backend.product.internal

import com.seanyoung.backend.common.model.IdentifiableEntity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Instant

@Table(value = "product")
@Suppress("LongParameterList")
class ProductEntity(
    @Id
    override var id: Long = 0,
    val name: String,
    val qty: Long,
    val price: BigDecimal,
    val imageUrl: String?,
    val creationTimestamp: Instant,
    val modificationTimestamp: Instant?
) : IdentifiableEntity
