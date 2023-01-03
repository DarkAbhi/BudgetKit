package com.example.budgetkit

import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.budgetkit.models.SmsModel
import com.example.budgetkit.ui.theme.ComposeTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {

    val array =
        arrayOf(Telephony.Sms._ID, Telephony.Sms.BODY, Telephony.Sms.ADDRESS, Telephony.Sms.DATE)
    val list = ArrayList<SmsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
        getMessages()
    }

    private fun getMessages() {
        val uri = Uri.parse("content://sms/inbox")
        val cursor = contentResolver.query(uri, array, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val id = cursor.getColumnIndex(Telephony.Sms._ID)
            val body = cursor.getColumnIndex(Telephony.Sms.BODY)
            val address = cursor.getColumnIndex(Telephony.Sms.ADDRESS)
            val time = cursor.getColumnIndex(Telephony.Sms.DATE)
            do {
                list.add(SmsModel(cursor.getLong(id),
                    cursor.getString(body).substring(0, 1),
                    cursor.getString(address),
                    cursor.getLong(time)))
            } while (cursor.moveToNext())
            cursor.close()
            Timber.i("LIST = $list")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Greeting("Android")
    }
}