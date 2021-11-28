package com.gandergusser.ganderguesser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.genderize.io/"
private var userGender = ""
private var MALE = "male"
private var FEMALE = "female"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this.applicationContext, "Please enter your first name", Toast.LENGTH_SHORT).show()

        val editText = findViewById<EditText>(R.id.editText_name)
        editText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                getMyData(editText.text.toString())
                return@OnKeyListener true
            }
            false
        })
    }
    private fun getMyData (userInput: String){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getGenderName(userInput)

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val responseBody = response.body()!!
                userGender = responseBody.gender

                val intentMale = Intent(applicationContext, Male::class.java)
                val intentFemale = Intent(applicationContext, Female::class.java)

                when (userGender) {
                    MALE -> {
                        startActivity(intentMale)
                    }
                    FEMALE -> {
                        startActivity(intentFemale)
                    }
                    null -> {
                        Toast.makeText(applicationContext, "Please try a different name", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }
}