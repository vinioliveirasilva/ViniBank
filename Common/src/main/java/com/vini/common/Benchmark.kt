package com.vini.common

import kotlin.system.measureTimeMillis

object Benchmark {
    fun <T> bench(name: String, block: () -> T) : T {
        var result: T? = null
        val timeInMillis = measureTimeMillis {
            result = block()
        }
        println("#BENCHMARK $name -> (The operation took $timeInMillis ms)")
        return result!!
    }
}