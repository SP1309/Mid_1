package com.example.midterm_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AppTwoActivity : AppCompatActivity() {

    private lateinit var mySpinner: Spinner
    private lateinit var myButton: Button
    private var selectedCarCompany: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apptwo)

        mySpinner = findViewById(R.id.mySpinner)
        myButton = findViewById(R.id.myButton)

        // Setting up the Spinner
        val carCompanies = arrayOf("Toyota Camry", "Ford F-150", "BMW 3 Series", "Honda Civic", "Chevrolet Camaro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carCompanies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mySpinner.adapter = adapter

        // Spinner listener
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCarCompany = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCarCompany = "Toyota" // default car company
            }
        }

        // Button listener
        myButton.setOnClickListener {
            // Create an intent to start the DisplayActivity
            val intent = Intent(this@AppTwoActivity, DisplayActivity::class.java)
            // Pass the selected car company to the new activity
            intent.putExtra("selectedCarCompany", selectedCarCompany)
            startActivity(intent)
        }
    }
}
