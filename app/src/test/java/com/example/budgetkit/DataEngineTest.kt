package com.example.budgetkit

import com.example.budgetkit.engine.extractAmountSpent
import com.example.budgetkit.engine.extractBalance
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DataEngineTest {
    private val exampleMessages = listOf(
        "You've spent Rs.400 On HDFC Bank CREDIT Card xx0803 At K R J FUEL STATION On 2023-01-01:17:27:13 Avl bal: Rs.191099 Curr O/s: Rs.8901 Not you?Call 18002586161",
        "Your A/C XXXXX101755 is debited by INR 87,180.00 on 31/12/22 14:57. New Bal :INR 29,400.06. Call us on 18004194332 for dispute. Team IDFC FIRST Bank",
        "Rs.40.00 is debited from Kotak Bank a/c XXXX4987 to pinelabs.838019@icici on 31-12-22. To report fraud/raise dispute, click kotak.com/fraud. New balance: Rs. 2,184.36",
        "Rs.110.00 is credited in your Kotak Bank a/c XXXX4987 by UPI ID tk3em3@okicici on 01-07-22 (UPI Ref no 218202431479). New balance: Rs. 58,492.80",
        "An amount of INR 1,771.00 has been DEBITED to your account XXXXX68255 on 08/12/2022. Total Avail.bal INR 2.55. - Canara Bank",
        "Cash withdrawal of Rs200.00 made on Kotak 811 Debit Card XX5061 on 26/12/2022 at 504492.Avl bal is Rs 1289.36.Not you?Visit kotak.com/fraud. Transactions on non-Kotak ATMs are chargeable beyond 5 per month(3 for Metro locations), if applicable, as per your account variant. Visit www.kotak.com for details.",
        "Thank you for using your Kotak 811 Debit Card XX5061 for Rs960.00 at RETAIL  CC MUM on 29/06/2022.Avl bal is Rs.66357.41.Not you?Visit kotak.com/fraud",
        "Thank you for using your Kotak Debit Card X5061 for Rs824.82 at MICROSOFT*STORE on 11-10-2021.Avl bal is Rs.2,194.36.Not you?Visit kotak.com/fraud",
    )

    @Test
    fun checkBalance() {
        exampleMessages.forEach {
            assert(it.extractBalance()!=null)
        }
    }

    @Test
    fun checkAmountSpent() {
        exampleMessages.forEach {
            assert(it.extractAmountSpent()!=null)
        }
    }

}