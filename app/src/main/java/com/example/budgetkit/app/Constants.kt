package com.example.budgetkit.app

import android.provider.Telephony

val SMS_DATA = arrayOf(Telephony.Sms._ID, Telephony.Sms.BODY, Telephony.Sms.ADDRESS, Telephony.Sms.DATE)

const val PARAM_TRANSACTION_ID = "transactionId"
const val PARAM_TRANSACTION_CATEGORY = "transactionCategory"