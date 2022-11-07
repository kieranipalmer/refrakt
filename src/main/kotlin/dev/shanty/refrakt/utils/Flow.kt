package dev.shanty.refrakt.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeoutOrNull

fun <T> Flow<T>.timeoutAfter(duration: Long) = flow<T> {
    withTimeoutOrNull(duration) {
        this@timeoutAfter.collect { emit(it) }
    }
}
