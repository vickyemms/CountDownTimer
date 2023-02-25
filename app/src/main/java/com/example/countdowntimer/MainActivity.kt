package com.example.countdowntimer

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById<ConstraintLayout>(R.id.root)
        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        timer = object : CountDownTimer(10_000, 1_000){
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTick(remaining: Long) {
                textView.text = (remaining/1000).toString()
                root.setBackgroundColor(getRandomColor().toArgb())
            }

            override fun onFinish() {
                textView.text = "Done!"
            }
        }

        button.setOnClickListener{
            timer.start()
        }

    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRandomColor(): Color{
        return Color.valueOf(
            Random.nextFloat() * 255,
            Random.nextFloat() * 255,
            Random.nextFloat() * 255,
        )
    }

}