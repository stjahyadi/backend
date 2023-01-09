package com.seanyoung.backend.product.internal

import com.seanyoung.backend.common.exception.InvalidRequestException
import com.seanyoung.backend.product.ProductRepository
import com.seanyoung.backend.product.internal.v1.Product
import com.seanyoung.backend.product.mapToEntity
import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
private val log = KotlinLogging.logger {}

@Service
class InternalProductService(
    private val productRepository: ProductRepository
) {

    @Transactional
    fun createProduct(product: Product): ProductEntity {
        val productEntity = product.mapToEntity(creationTimestamp = Instant.now())
        val savedProductEntity = productRepository.save(productEntity)

        return savedProductEntity
    }

    @Transactional(readOnly = true)
    fun getProductById(id: Long): ProductEntity {
        return productRepository.findByIdOrNull(id) ?: throw ProductNotFoundException("Could not find Product with id=$id")
    }

    @Transactional
    fun updateProduct(id: Long, product: Product) {
        if (product.id != 0L && product.id != id) {
            throw InvalidRequestException("Invalid update request; ID=$id but Product#id=${product.id}")
        }
        val existingProduct = getProductById(id)
        val productEntity = product.mapToEntity(
            creationTimestamp = existingProduct.creationTimestamp,
            modificationTimestamp = Instant.now()
        )
        productEntity.id = id
        productRepository.save(productEntity)
    }

    @Transactional
    fun deleteProductById(id: Long) {
        val toBeDeletedProductEntity = productRepository.findByIdOrNull(id)
        if (toBeDeletedProductEntity == null) {
            log.warn { "Tried to delete non-existent Product with id=$id" }
            return
        }
        productRepository.deleteById(id)
        log.info { "Product (id=$id) deleted successfully" }
    }
}
