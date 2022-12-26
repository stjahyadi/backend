package com.seanyoung.backend.product

import com.seanyoung.backend.product.internal.ProductEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<ProductEntity, Long>
