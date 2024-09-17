package com.example.final_app

import android.os.Bundle
import android.widget.Toast
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.final_app.model.DataClass


class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var detailName: TextView
    private lateinit var detailCulture: TextView
    private lateinit var detailDomain: TextView
    private lateinit var detailSymbol: TextView
    private lateinit var detailParentage: TextView
    private lateinit var detailRomanEquivalent: TextView
    private lateinit var detailDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize UI elements
        detailName = findViewById(R.id.detailName)
        detailCulture = findViewById(R.id.detailCulture)
        detailDomain = findViewById(R.id.detailDomain)
        detailSymbol = findViewById(R.id.detailSymbol)
        detailParentage = findViewById(R.id.detailParentage)
        detailRomanEquivalent = findViewById(R.id.detailRomanEquivalent)
        detailDesc = findViewById(R.id.detailDesc)

        // Retrieve the data passed from the previous activity
        val data = intent.getParcelableExtra<DataClass>("data")

        if (data != null) {
            // Set data to ViewModel
            detailViewModel.setData(data)
            detailViewModel.data.observe(this, Observer { dataClass ->
                // Update UI with data from ViewModel
                detailName.text = dataClass.name
                detailCulture.text = dataClass.culture
                detailDomain.text = dataClass.domain
                detailSymbol.text = dataClass.symbol
                detailParentage.text = dataClass.parentage
                detailRomanEquivalent.text = dataClass.romanEquivalent ?: "N/A" // Handle null value
                detailDesc.text = dataClass.description
            })
        } else {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show()
        }
    }
}
