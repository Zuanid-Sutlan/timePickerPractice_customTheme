package com.example.timepickerpractice_customtheme

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setInputMode(INPUT_MODE_CLOCK)
                .setTitleText("Select Appointment time")
//                .setTheme(R.style.CustomMaterialTimePicker)
                .build()

        val tvDate = findViewById<TextView>(R.id.tvDate)
        tvDate.setOnClickListener{
            picker.show(supportFragmentManager, "TAG_TIME_PICKER")
        }

        picker.addOnPositiveButtonClickListener {
            // Handle the selected time
            val selectedHour = picker.hour
            val selectedMinute = picker.minute

            // Format the selected time
            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)

            // Update TextView with selected time
            tvDate.text = selectedTime
        }
    }
}