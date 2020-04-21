package com.colors.you
/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "error"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 **/
sealed class Either<out L, out R> {

    /** Represents the left side of [Either] class which by convention is a "Failure" **/
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /** Represents the right side of [Either] class which by convention is a "Success" **/
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    fun either(left: (L) -> Any, right: (R) -> Any): Any =
        when (this) {
            is Left -> left(a)
            is Right -> right(b)
        }

    fun <A, B> either(left: (L) -> Either<A, B>, right: (R) -> Either<A, B>): Either<A, B> =
        when (this) {
            is Left -> left(a)
            is Right -> right(b)
        }
}