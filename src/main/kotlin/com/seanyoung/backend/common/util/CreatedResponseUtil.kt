package com.seanyoung.backend.common.util

import org.springframework.http.ResponseEntity
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

fun <T> createdResponse(resourceId: Any, resource: T): ResponseEntity<T> {
    val createdResourceUri: URI = ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(resourceId).toUri()
    return ResponseEntity.created(createdResourceUri).body(resource)
}
