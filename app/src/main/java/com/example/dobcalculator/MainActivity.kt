package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var tvSelectedDate :TextView? = null
    var tvInMinutes :TextView? = null
    var tvInHours :TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvInMinutes = findViewById(R.id.tvInMinutes)
        tvInHours = findViewById(R.id.tvInHours)
        btnDatePicker.setOnClickListener {
            callDatePicker()
        }
    }

    private fun callDatePicker() {
       var myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this , DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDay->
            Toast.makeText(this,"DatePicker is working",Toast.LENGTH_SHORT).show()
            var selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selecteDateInMinutes = theDate.time/60000
            val SelectedDateInHour = selecteDateInMinutes/60
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time/60000
            val currentTimeInHour = currentDateInMinutes/60
            val difference = currentDateInMinutes - selecteDateInMinutes
            val differenceInHour = currentTimeInHour - SelectedDateInHour
            tvInMinutes?.text = difference.toString()
            tvInHours?.text = differenceInHour.toString()

        },year,month,day).show()
    }
}