package com.colors.you.repository

import com.colors.you.Constants.NAMES_GENERATED
import com.colors.you.Either
import com.colors.you.domain.RandomNameRepository
import java.util.*

class RandomNameRepositoryImpl(
    private val nameGeneratorAPI: NameGeneratorAPI
) : RandomNameRepository {
    override fun getNewName(): Either<Throwable, String> {
        return try {
            val response = nameGeneratorAPI.getRandomName(NAMES_GENERATED).execute()
            when (response.isSuccessful) {
                true -> {
                    val list = response.body()
                    if (list != null && list.isNotEmpty()) {
                        Either.Right(list.first())
                    } else {
                        Either.Left(
                            MissingResourceException(
                                "API failed to give us a name?",
                                "",
                                ""
                            )
                        )
                    }


                }
                false -> Either.Left(
                    MissingResourceException(
                        response.errorBody().toString(),
                        "",
                        ""
                    )
                )
            }
        } catch (e: Exception) {
            Either.Left(e)
        }

    }
}