package com.seanyoung.backend.product.internal.v1

import com.seanyoung.backend.common.exception.Problem
import com.seanyoung.backend.common.util.createdResponse
import com.seanyoung.backend.product.internal.InternalProductService
import com.seanyoung.backend.product.mapFromEntity
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Positive

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/internal/v1/products")
@Tag(name = "Products")
@Validated
class InternalProductController(private val internalProductService: InternalProductService) {

    @GetMapping("/{id}")
    @ApiResponse(
        responseCode = "404",
        description = "Not found",
        content = [Content(schema = Schema(implementation = Problem::class))]
    )
    fun getAwardLabelById(
        @Positive
        @PathVariable
        id: Long
    ): Product {
        return internalProductService.getProductById(id).mapFromEntity()
    }

    @PostMapping
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "New product is created"),
        ApiResponse(
            responseCode = "409",
            description = "Validation failed",
            content = [Content(schema = Schema(implementation = Problem::class))]
        )
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @Valid @RequestBody
        product: Product
    ): ResponseEntity<Product> {
        val createdProduct = internalProductService.createProduct(product).mapFromEntity()
        log.info { "Product (id=${createdProduct.id} created successfully" }
        return createdResponse(createdProduct.id, createdProduct)
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "The existing product is updated successfully")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBannerById(
        @Positive @PathVariable
        id: Long,
        @Valid @RequestBody
        product: Product
    ) {
        internalProductService.updateProduct(id, product)
        log.info { "Product (id=$id) updated successfully" }
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "The product is deleted")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBannerById(
        @Positive @PathVariable
        id: Long
    ) {
        internalProductService.deleteProductById(id)
    }
}
