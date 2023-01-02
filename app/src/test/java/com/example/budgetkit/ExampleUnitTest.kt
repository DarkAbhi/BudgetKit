package com.example.budgetkit

import com.example.budgetkit.engine.extractAmountSpent
import com.example.budgetkit.engine.extractBalance
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val exampleMessages = listOf(
        ""
    )

    @Test
    fun checkBalance() {
        exampleMessages.forEach {
            assert(it.extractBalance()!=-0.0)
        }
    }

    @Test
    fun checkAmountSpent() {
        exampleMessages.forEach {
            it.extractAmountSpent()
        }
    }

}