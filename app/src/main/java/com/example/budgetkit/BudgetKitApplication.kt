package com.example.budgetkit

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree


class BudgetKitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}