package com.gandergusser.ganderguesser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Female : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_female)

        val back = findViewById<Button>(R.id.back_one2)

        back.setOnClickListener(View.OnClickListener {
            val intentMain = Intent(applicationContext, MainActivity::class.java)
            startActivity(intentMain)
        })
    }
}