package com.example.budgetkit.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import com.example.budgetkit.models.NewSmsModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

class SmsListener : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val newSmsModel = getTextFromSms(intent?.extras)
        if (newSmsModel?.message != null && newSmsModel.timestamp != null)
            Timber.d("SMS = ${newSmsModel.message} TIME = ${
                SimpleDateFormat("MMMM dd, yyyy",
                    Locale.US).format(newSmsModel.timestamp)
            }")
    }

    private fun getTextFromSms(extras: Bundle?): NewSmsModel? {
        val pdus = extras?.get("pdus") as Array<*>
        val format = extras.getString("format")
        for (pdu in pdus) {
            val smsmsg = getSmsMsg(pdu as ByteArray?, format)
            return NewSmsModel(smsmsg?.displayMessageBody, smsmsg?.timestampMillis)
        }
        return null
    }

    private fun getSmsMsg(pdu: ByteArray?, format: String?): SmsMessage? {
        return SmsMessage.createFromPdu(pdu, format)
    }

}