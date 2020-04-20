package com.colors.you

import android.content.Context
import android.content.SharedPreferences
import com.colors.you.domain.CacheRepository
import com.colors.you.domain.RandomColorRepository
import com.colors.you.repository.CacheRepositoryImpl
import com.colors.you.repository.RandomColorRepositoryImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewColorHexTest {
    companion object {
        const val TEST_HEX = "#123456"
        const val HEX_REG_EX = "^[#][a-fA-F0-9]{6}$"
    }

    private val sharedPreferences: SharedPreferences = mock()
    private val sharedPreferencesEditor: SharedPreferences.Editor = mock()
    private  val colorRepository: RandomColorRepository = RandomColorRepositoryImpl()
    private lateinit var cacheRepository: CacheRepository

    @Before
    fun before() {
        whenever(sharedPreferences.getString(any(), anyOrNull())).thenReturn(TEST_HEX)
        cacheRepository = CacheRepositoryImpl(sharedPreferences)
    }

    @Test
    fun getNewColor() {
        val color = colorRepository.getNewColor()
        assert(color.length == 7)
        assert(HEX_REG_EX.toRegex().matches(color))
    }

    @Test
    fun getOldColor() {
        val color = cacheRepository.getColor()
        assert(color == TEST_HEX)
    }
}