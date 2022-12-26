package com.seanyoung.backend.product.internal

import com.seanyoung.backend.product.ProductRepository
import com.seanyoung.backend.product.internal.v1.Product
import com.seanyoung.backend.product.mapToEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional
class InternalProductService(
    private val productRepository: ProductRepository
) {

    fun createProduct(product: Product): ProductEntity {
        val productEntity = product.mapToEntity(creationTimestamp = Instant.now())
        val savedProductEntity = productRepository.save(productEntity)

        return savedProductEntity
    }
}
