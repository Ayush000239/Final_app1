package com.example.final_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> get() = _loginResult

    fun performLogin(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://vu-nit3213-api.onrender.com/sydney/auth")
            val connection = url.openConnection() as HttpURLConnection
            try {
                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/json")

                // Create JSON request body
                val requestBody = JSONObject().apply {
                    put("username", username)
                    put("password", password)
                }.toString()

                // Send request
                connection.outputStream.use {
                    it.write(requestBody.toByteArray())
                    it.flush()
                }

                // Check response
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    val response = StringBuilder()
                    reader.forEachLine { response.append(it) }
                    reader.close()

                    // Parse JSON response
                    val jsonResponse = JSONObject(response.toString())
                    val keypass = jsonResponse.getString("keypass")

                    // Post result to LiveData
                    _loginResult.postValue(keypass)
                } else {
                    _loginResult.postValue(null)
                }
            } catch (e: Exception) {
                _loginResult.postValue(null)
            } finally {
                connection.disconnect()
            }
        }
    }
}
