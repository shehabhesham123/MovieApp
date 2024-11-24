package com.example.movieapp.core.usecase

import com.example.movieapp.core.failure.Failure
import com.example.movieapp.core.funcational.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class Usecase<in Param, out Type : Any> {
    abstract suspend fun run(param: Param): Either<Failure, Type>

    operator fun invoke(
        param: Param,
        scope: CoroutineScope,
        onResult: (Either<Failure, Type>) -> Unit
    ) {
        scope.launch {
            val deferredJob = async(Dispatchers.IO) {
                run(param)
            }
            onResult(deferredJob.await())
        }
    }

    /** for none params */
    class None()
}