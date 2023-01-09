package com.seanyoung.backend.product

import com.seanyoung.backend.product.internal.ProductEntity
import com.seanyoung.backend.product.internal.v1.Product
import java.time.Instant

fun Product.mapToEntity(creationTimestamp: Instant, modificationTimestamp: Instant? = null): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        qty = this.qty,
        price = this.price,
        imageUrl = this.imageUrl,
        creationTimestamp = creationTimestamp,
        modificationTimestamp = modificationTimestamp
    )
}

fun ProductEntity.mapFromEntity(): Product {
    return Product(
        id = this.id,
        name = this.name,
        qty = this.qty,
        price = this.price,
        imageUrl = this.imageUrl
    )
}
