package com.seanyoung.backend.product.internal.v1

import com.seanyoung.backend.common.util.createdResponse
import com.seanyoung.backend.product.internal.InternalProductService
import com.seanyoung.backend.product.mapFromEntity
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/internal/v1/products")
@Tag(name = "Products")
@Validated
class InternalProductController(private val internalProductService: InternalProductService) {

    @PostMapping
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "New product is created")
        ]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody product: Product): ResponseEntity<Product> {
        val createdProduct = internalProductService.createProduct(product).mapFromEntity()
        log.info { "Product (id=${createdProduct.id} created successfully" }
        return createdResponse(createdProduct.id, createdProduct)
    }
}
