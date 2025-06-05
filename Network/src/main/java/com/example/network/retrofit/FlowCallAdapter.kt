package com.example.network.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type

class FlowCallAdapter<R>(private val responseType: Type) : CallAdapter<R, Flow<R>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Flow<R> {
        return flow {
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.let { emit(it) }
            } else {

                throw RuntimeException("Network call failed: ${response.code()} ${readBody(response)}")
            }
        }
    }

    private fun <T> readBody(result: Response<T>): String? {
        val responseBody = result.errorBody()
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer

        return buffer?.clone()?.readUtf8()
    }
}