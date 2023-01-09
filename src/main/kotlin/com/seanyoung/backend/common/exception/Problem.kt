package com.seanyoung.backend.common.exception

import java.net.InetAddress
import java.net.URI

class Problem(
    val type: URI = URI.create("/problems/unhandled-exception"),
    val detail: String? = "An unhandled exception occurred.",
    val title: String = "Unknown problem (title not provided)",
    val status: Int = 500
) {
    val host: String = InetAddress.getLocalHost().hostName

    override fun toString(): String {
        return detail.toString()
    }
}
