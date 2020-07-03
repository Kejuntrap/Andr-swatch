package com.example.helloworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import java.util.*

var lastValue: Long = 0;
var timeValue: Long = 0;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeText = findViewById(R.id.timeText) as TextView
        val startButton = findViewById(R.id.startButton) as Button
        val stopButton = findViewById(R.id.stopButton) as Button
        val handler = Handler()

        var runnable = object : Runnable {
            override fun run() {
                timeValue = Math.max(System.currentTimeMillis() - lastValue , 0);
                handler.postDelayed(this, 16);
                timeText.text = timeToText(timeValue)
            }
        }

        startButton.setOnClickListener {
            lastValue = System.currentTimeMillis()
            handler.post(runnable)
        }
        stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
        }
    }
}

private fun timeToText(time: Long = 0): String {
    if (time == 0L) {
        return "00:00:000"
    } else {
        val mi = (time % 3600000) / 60000
        val se = (time % 60000) / 1000
        val ms = time % 1000
        return "%1$02d:%2$02d:%3$03d".format(mi, se , ms)
    }
}