package com.example.budgetkit.engine

import timber.log.Timber

val balanceKeywords = listOf(
    "avbl bal",
    "available balance",
    "a/c bal",
    "available bal",
    "avl bal",
    "new bal", // IDFC Bank
    "new balance", // Kotak Bank
    "total avail.bal" // Canara Bank
)

val currencyKeywords = listOf("rs","rs.","rs .","inr")

fun String.extractBalance():Double {
    val message = this.processMessage()
    // find matches present ["rs."]
    val match = balanceKeywords.filter { message.contains(it) }
    if (match.isNotEmpty()) {
        val index = message.indexOf(match.first())
        val balanceMessage = message.substring(index)
        val matchCurrencyKeyword = currencyKeywords.filter { balanceMessage.contains(it) }.toMutableList()
        if ("rs." in matchCurrencyKeyword) matchCurrencyKeyword.remove("rs")
        if (matchCurrencyKeyword.isNotEmpty()) {
            var balanceString = balanceMessage.substring(balanceMessage.indexOf(matchCurrencyKeyword.first()) + matchCurrencyKeyword.first().length).trim()
            if (balanceString.contains(' ')) {
                balanceString = balanceString.substring(0, balanceString.indexOf(' ')).replace("[^\\d.]".toRegex(), "")
            }
            balanceString = balanceString.replace(",","")
            balanceString = balanceString.dropLastWhile {
                it == '.'
            }
            return balanceString.toDouble()
        }
    }
    return -0.0
}

fun String.extractAccountType() {
    var message= this.processMessage()
//    if (message.contains("credit card") || message.contains("creditcard"))
}

fun String.extractAmountSpent(): Double {
    var message = this.processMessage()
    val balanceMatch = balanceKeywords.filter { message.contains(it) }
    val index = message.indexOf(balanceMatch.first())
    message = message.substring(0,index)
    val matchCurrencyKeyword = currencyKeywords.filter { message.contains(it) }.toMutableList()
    if ("rs." in matchCurrencyKeyword) matchCurrencyKeyword.remove("rs")
    if (matchCurrencyKeyword.isNotEmpty()) {
        val currencyMatchIndex = message.indexOf(matchCurrencyKeyword.first())
        message = message.substring(currencyMatchIndex + matchCurrencyKeyword.first().length).trim()
        message=message.substring(0,message.indexOf(' ')).replace("[^\\d.]".toRegex(), "").dropLastWhile {
            it == '.'
        }
        println(message)
        return message.toDouble()
    }
    return -0.0
}

fun String.processMessage(): String {
    // make lowercase
    var message = this.lowercase()
    // remove "first" for IDFC First Bank in SMS
    message = message.replace("first", "")
    return message
}

fun some(){
    var message =""
    // convert to lowercase
    message = message.lowercase()
    // remove '-'
    message = message.replace("-", "")
    // remove ':'
    message = message.replace(":", "")
    // remove '/'
    message = message.replace("/", "")
    // remove 'ending'
    message = message.replace("/", "");
    // replace 'x'
    message = message.replace("x", "");
    // replace 'is'
    message = message.replace("is","");
    // replace 'with'
    message = message.replace("with","")
    // remove 'no.'
    message = message.replace("no .","")
    message = message.replace("no.","")
    // replace all ac, acct, account with ac
    message = message.replace("/\bac\b|\bacct\b|\baccount\b/g".toRegex(), "ac")
    // replace all 'rs' with 'rs. '
    message = message.replace("rs", "rs. ")
    // replace all 'rs ' with 'rs. '
    message = message.replace("rs ", "rs. ")
    // replace all inr with rs.
    message = message.replace("inr ", "rs. ")
    // replace all 'rs.' with 'rs. '
    message = message.replace("rs.", "rs. ")
    Timber.i("message = $message")
}