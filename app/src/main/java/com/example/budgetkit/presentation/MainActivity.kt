package com.example.budgetkit.presentation

import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetkit.app.SMS_DATA
import com.example.budgetkit.engine.decideCategory
import com.example.budgetkit.engine.extractAmountSpent
import com.example.budgetkit.models.SmsModel
import com.example.budgetkit.models.Transaction
import com.example.budgetkit.presentation.home_screen.HomeScreen
import com.example.budgetkit.presentation.transaction_detail_screen.TransactionDetailScreen
import com.example.budgetkit.presentation.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var list = ArrayList<SmsModel>()
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route
                ) {
                    composable(
                        route = Screen.HomeScreen.route
                    ) {
                        HomeScreen(navController)
                    }
                    composable(
                        route = Screen.TransactionDetailScreen.route + "/{transactionId}"
                    ) {
                        TransactionDetailScreen()
                    }
                }

            }
        }
        getMessages()
    }

    private fun getMessages() {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        // get start of the month
        cal.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonthInMilliSeconds = cal.timeInMillis
        Timber.d("Start of the month:       " + cal.time)
        Timber.d("... in milliseconds: $firstDayOfMonthInMilliSeconds")
        val uri = Uri.parse("content://sms/inbox")
        val cursor = contentResolver.query(uri, SMS_DATA, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val id = cursor.getColumnIndex(Telephony.Sms._ID)
            val body = cursor.getColumnIndex(Telephony.Sms.BODY)
            val address = cursor.getColumnIndex(Telephony.Sms.ADDRESS)
            val time = cursor.getColumnIndex(Telephony.Sms.DATE)
            do {
                list.add(
                    SmsModel(
                        cursor.getLong(id).toString(),
                        cursor.getString(body),
                        cursor.getString(address),
                        cursor.getLong(time)
                    )
                )
            } while (cursor.moveToNext() && cursor.getLong(time) > firstDayOfMonthInMilliSeconds)
            cursor.close()
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.addTransactions(
                    list.filter {
                        val amountSpent = it.body.extractAmountSpent()
                        amountSpent!= null&& amountSpent!=0.0
                    }.map {
                        Transaction(
                            it.id,
                            it.body.extractAmountSpent()!!,
                            it.body.decideCategory(),
                            it.timestamp,
                            it.body
                        )
                    }
                )
            }
        }
    }
}
