package com.seanyoung.backend

import com.fasterxml.jackson.databind.ObjectMapper
import com.seanyoung.backend.product.internal.InternalProductService
import com.seanyoung.backend.product.internal.v1.InternalProductController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(
    value = [
        InternalProductController::class
    ]
)
open class ControllerTestBase {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var mockInternalProductService: InternalProductService
}
