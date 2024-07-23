package com.example.midterm_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    private val toyota = Car("Toyota Camry", "2023", "2.5L 4-cylinder engine", "$25,000")
    private val ford = Car("Ford F-150", "2023", "3.3L V6 engine", "$30,000")
    private val bmw = Car("BMW 3 Series", "2023", "2.0L 4-cylinder engine", "$41,000")
    private val honda = Car("Honda Civic", "2023", "2.0L 4-cylinder engine", "$22,000")
    private val chev = Car("Chevrolet Camaro", "2023", "6.2L V8 engine", "$40,000")

    private val carlist = arrayOf(toyota, ford, bmw, honda, chev)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val displayTextView = findViewById<TextView>(R.id.displayTextView)

        val displayCarName = findViewById<TextView>(R.id.displayCarName)
        val displayLaunchYear = findViewById<TextView>(R.id.displayLaunchYear)
        val displayEngine = findViewById<TextView>(R.id.displayEngine)
        val displayCarPrice = findViewById<TextView>(R.id.displayCarPrice)

        // Get the intent and retrieve the Car object
        val selectedCar = intent.getStringExtra("selectedCarCompany")
        val selectedCarModel = getCar(selectedCar, carlist)

        selectedCarModel?.let {
            // Display the car details
            displayCarName.text = it.carName
            displayLaunchYear.text = it.launchYear
            displayEngine.text = it.engine
            displayCarPrice.text = it.carPrice
        }

        // Display the selected car company
        // displayTextView.text = "Selected Car Company: $selectedCarCompany"
    }

    private fun getCar(car: String?, carlist: Array<Car>): Car? {
        for (c in carlist) {
            if (c.carName == car) return c
        }
        return null
    }
}

data class Car(
    val carName: String,
    val launchYear: String,
    val engine: String,
    val carPrice: String
)
