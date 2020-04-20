package com.colors.you

import android.content.Context
import android.content.SharedPreferences
import com.colors.you.domain.RandomColorRepository
import com.colors.you.repository.RandomColorRepositoryImpl
import com.nhaarman.mockitokotlin2.any
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
    private lateinit var colorRepository: RandomColorRepository

    @Before
    fun before() {
        whenever(sharedPreferences.getString(any(), any())).thenReturn(TEST_HEX)
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        colorRepository = RandomColorRepositoryImpl(sharedPreferences)

    }

    @Test
    fun getNewColor() {
        val color = colorRepository.getNewColor()
        assert(color.length == 7)
        assert(HEX_REG_EX.toRegex().matches(color))
    }

    @Test
    fun getOldColor() {
        val color = colorRepository.getColor()
        assert(color == TEST_HEX)
    }
}