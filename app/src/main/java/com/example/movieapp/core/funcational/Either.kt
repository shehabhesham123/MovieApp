package com.example.movieapp.core.funcational

import com.example.movieapp.core.funcational.Either.Left
import com.example.movieapp.core.funcational.Either.Right

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {

    /**
     * Represents the left side of [Either] class which by convention
     * is a "Failure".
     */
    data class Left<out L>
    @Deprecated(".toLeft()", ReplaceWith("a.toLeft()"))
    constructor(val left: L) : Either<L, Nothing>()

    /**
     * Represents the right side of [Either] class which by convention
     * is a "Success".
     */
    data class Right<out R>
    @Deprecated(".toRight()", ReplaceWith("b.toRight()"))
    constructor(val right: R) : Either<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left<L>

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     * @see Left
     * @see Right
     */
    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }

    /**
     * Applies fnL if this is a Left or fnR if this is a Right.
     *
     * Kotlin Coroutines Support.
     * @see Left
     * @see Right
     */
    suspend fun <T> coFold(fnL: suspend (L) -> T, fnR: suspend (R) -> T): T =
        when (this) {
            is Left -> fnL(left)
            is Right -> fnR(right)
        }


}
