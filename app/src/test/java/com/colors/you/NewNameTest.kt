package com.colors.you

import com.colors.you.domain.RandomNameRepository
import com.colors.you.repository.NameGeneratorAPI
import com.colors.you.repository.RandomNameRepositoryImpl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class NewNameTest {
    companion object {
        const val TEST_RESPONSE = "[\"test\"]"
        const val TEST_GARBAGE = "8iyh7ujn5tgv4rfc9uj8miko5tgvrf4e65tgyh7ujioi"
        const val TEST_NAME = "test"
    }

    private var mockWebServer = MockWebServer()
    private lateinit var mockClient: NameGeneratorAPI
    private lateinit var randomNameRepositoryImpl: RandomNameRepository

    @Before
    fun before() {
        mockClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(NameGeneratorAPI::class.java)

        randomNameRepositoryImpl = RandomNameRepositoryImpl(mockClient)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun response_is_converted() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(TEST_RESPONSE)

        mockWebServer.enqueue(response)

        val name = randomNameRepositoryImpl.getNewName()
        name.either({
            Either.Left(it)
        }, {
            assert(it == TEST_NAME)
            Either.Right(it)
        })
    }

    @Test
    fun response_is_garbage_handle_discreetly() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(TEST_GARBAGE)

        mockWebServer.enqueue(response)

        val name = randomNameRepositoryImpl.getNewName()
        name.either({
            assert(it is IllegalArgumentException)
            Either.Left(it)
        }, {
            Either.Right(it)
        })
    }

}