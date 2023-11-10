package pratiksha.dob_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }

    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth)

                val currentDate = Calendar.getInstance()

                val years = currentDate.get(Calendar.YEAR) - selectedDate.get(Calendar.YEAR)
                val months = currentDate.get(Calendar.MONTH) - selectedDate.get(Calendar.MONTH)
                val days = currentDate.get(Calendar.DAY_OF_MONTH) - selectedDate.get(Calendar.DAY_OF_MONTH)
                val hours = currentDate.get(Calendar.HOUR_OF_DAY) - selectedDate.get(Calendar.HOUR_OF_DAY)
                val minutes = currentDate.get(Calendar.MINUTE) - selectedDate.get(Calendar.MINUTE)

                val ageText = StringBuilder()

                if (years != 0) {
                    ageText.append("${Math.abs(years)} years ")
                }
                if (months != 0) {
                    ageText.append("${Math.abs(months)} months ")
                }
                if (days != 0) {
                    ageText.append("${Math.abs(days)} days ")
                }
                if (hours != 0) {
                    ageText.append("${Math.abs(hours)} hours ")
                }
                if (minutes != 0) {
                    ageText.append("${Math.abs(minutes)} minutes")
                } else if (ageText.isEmpty()) {
                    ageText.append("0 minutes")
                }

                tvAgeInMinutes?.text = ageText.toString()
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

}