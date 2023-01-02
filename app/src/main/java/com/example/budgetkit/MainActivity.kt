package com.example.budgetkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.budgetkit.engine.extractBalance
import com.example.budgetkit.engine.processMessage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}