package com.seanyoung.backend.product.internal.v1

import com.fasterxml.jackson.module.kotlin.readValue
import com.seanyoung.backend.ControllerTestBase
import com.seanyoung.backend.MockTestData.product
import com.seanyoung.backend.product.mapToEntity
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.post
import java.time.Instant

class InternalProductControllerTest : ControllerTestBase() {
    private val productId: Long = 999999
    private val unsavedProduct = product()

    @Test
    fun `controller creates product`() {
        val createdProduct = unsavedProduct.copy(id = productId)
        val createdProductEntity = createdProduct.mapToEntity(Instant.now())
        every {
            mockInternalProductService.createProduct(any())
        } returns createdProductEntity

        val resultActions = mockMvc.post(
            "/v1/internal/product"
        ) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(unsavedProduct)
        }.andExpect {
            status {
                isCreated()
            }
        }

        val result: MvcResult = resultActions.andReturn()
        val contentAsString = result.response.contentAsString
        val response: Product = objectMapper.readValue(contentAsString)
        assertEquals(createdProduct.id, response.id)
    }
}
