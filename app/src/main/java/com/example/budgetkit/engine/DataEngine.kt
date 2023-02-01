package com.example.budgetkit.engine

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

val currencyKeywords = listOf("rs", "rs.", "rs .", "inr")

fun String.extractBalance(): Double? {
    val message = this.processMessage()
    // find matches present for balance
    val match = balanceKeywords.filter { message.contains(it) }
    if (match.isNotEmpty()) {
        val index = message.indexOf(match.first())
        val balanceMessage = message.substring(index)
        // find matches present ["rs."]
        val matchCurrencyKeyword =
            currencyKeywords.filter { balanceMessage.contains(it) }.toMutableList()
        if ("rs." in matchCurrencyKeyword) matchCurrencyKeyword.remove("rs")
        if (matchCurrencyKeyword.isNotEmpty()) {
            var balanceString =
                balanceMessage.substring(balanceMessage.indexOf(matchCurrencyKeyword.first()) + matchCurrencyKeyword.first().length)
                    .trim()
            if (balanceString.contains(' ')) {
                balanceString = balanceString.substring(0, balanceString.indexOf(' '))
                    .replace("[^\\d.]".toRegex(), "")
            }
            balanceString = balanceString.replace(",", "")
            balanceString = balanceString.dropLastWhile {
                it == '.'
            }
            return balanceString.toDoubleOrNull()
        }
    }
    return null
}

fun String.extractAccountType() {
    var message = this.processMessage()
//    if (message.contains("credit card") || message.contains("creditcard"))
}

fun String.extractAmountSpent(): Double? {
    try {
        var message = this.processMessage()
        val balanceMatch = balanceKeywords.filter { message.contains(it) }
        if (balanceMatch.isNotEmpty()) {
            val index = message.indexOf(balanceMatch.first())
            message = message.substring(0, index)
        }
        val matchCurrencyKeyword = currencyKeywords.filter { message.contains(it) }.toMutableList()
        if ("rs." in matchCurrencyKeyword) matchCurrencyKeyword.remove("rs")
        if (matchCurrencyKeyword.isNotEmpty()) {
            val currencyMatchIndex = message.indexOf(matchCurrencyKeyword.first())
            message =
                message.substring(currencyMatchIndex + matchCurrencyKeyword.first().length).trim()
            message = message.substring(0, message.indexOf(' ')).replace("[^\\d.]".toRegex(), "")
                .dropLastWhile {
                    it == '.'
                }
            return message.toDoubleOrNull()
        }
    } catch (e: Exception) {
        return null
    }
    return null
}

fun String.decideCategory(): String {
    val category = mutableListOf<String>()
    val message = this.lowercase()
    if (FOOD_AND_DRINK_NAMES.any {
            message.contains(it)
        }) {
        category.add(FOOD_AND_DRINK_CATEGORY)
    }
    if (GROCERY_NAMES.any {
            message.contains(it)
        }) {
        category.add(GROCERIES_CATEGORY)
    }
    if (SHOPPING_NAMES.any {
            message.contains(it)
        }) {
        category.add(SHOPPING_CATEGORY)
    }
    if (TRAVEL_NAMES.any {
            message.contains(it)
        }) {
        category.add(TRAVELLING_CATEGORY)
    }
    if (FUEL_NAMES.any {
            message.contains(it)
        }) {
        category.add(FUEL_CATEGORY)
    }
    if (ENTERTAINMENT_NAMES.any {
            message.contains(it)
        }) {
        category.add(ENTERTAINMENT_CATEGORY)
    }
    if (category.isNotEmpty()) return category.first()
    return GENERAL_CATEGORY
}

fun String.processMessage(): String {
    // make lowercase
    var message = this.lowercase()
    // remove "first" for IDFC First Bank in SMS
    message = message.replace("first", "")
    return message
}
