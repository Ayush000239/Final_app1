package com.example.final_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.example.final_app.model.DataClass

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: AdapterClass
    private val dataList: ArrayList<DataClass> = arrayListOf()
    private val searchList: ArrayList<DataClass> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter

        val keypass = intent.getStringExtra("keypass") ?: run {
            Toast.makeText(this, "No keypass provided!", Toast.LENGTH_SHORT).show()
            return
        }

        fetchDashboardData(keypass)

        myAdapter.onItemClick = { data ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun fetchDashboardData(keypass: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://vu-nit3213-api.onrender.com/dashboard/mythology")
            val connection = url.openConnection() as HttpURLConnection
            var jsonResponse = ""

            try {
                connection.requestMethod = "GET"
                connection.setRequestProperty("Accept", "application/json")
                connection.setRequestProperty("Authorization", "Bearer $keypass")

                val responseCode = connection.responseCode
                Log.d("DashboardActivity", "Response Code: $responseCode")

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    val response = StringBuilder()
                    reader.forEachLine { response.append(it) }
                    reader.close()

                    jsonResponse = response.toString()
                    Log.d("DashboardActivity", "Response: $jsonResponse")

                    val jsonObject = JSONObject(jsonResponse)
                    val entities = jsonObject.optJSONArray("entities")

                    withContext(Dispatchers.Main) {
                        entities?.let {
                            for (i in 0 until it.length()) {
                                val item = it.getJSONObject(i)
                                val data = DataClass(
                                    name = item.optString("name"),
                                    culture = item.optString("culture"),
                                    domain = item.optString("domain"),
                                    symbol = item.optString("symbol"),
                                    parentage = item.optString("parentage"),
                                    romanEquivalent = item.optString("romanEquivalent"), // Ensure this matches your JSON structure
                                    description = item.optString("description")
                                )
                                dataList.add(data)
                                searchList.add(data)
                            }
                            myAdapter.notifyDataSetChanged()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@DashboardActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Error: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DashboardActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            } finally {
                connection.disconnect()
            }
        }
    }
}
