package com.seanyoung.backend.common

import com.seanyoung.backend.common.exception.Problem
import mu.KotlinLogging

class ProblemLogger {
    companion object {
        val log = KotlinLogging.logger {}
    }

}

fun Problem.log() {
    ProblemLogger.log.error("A problem occurred for a request: {}", this.toString())
}
