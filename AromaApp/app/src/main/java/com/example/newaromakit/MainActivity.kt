package com.example.newaromakit

import com.example.newaromakit.BluetoothManager as BM
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.util.Log
import android.view.animation.AlphaAnimation
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream


class MainActivity : AppCompatActivity() {

    private var socket = BM.socket

    val mmInStream: InputStream? = socket?.inputStream
    val mmOutStream: OutputStream? = socket?.outputStream
    val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val goalButton = findViewById<Button>(R.id.goalButton) // Goal focus button
        // Map of button messages
        val buttonPopups = mapOf(
            R.id.button1 to listOf("Do some self care", "Focus on this relaxed feeling"),
            R.id.button2 to listOf("Vent to a friend", "Write your feelings in a journal"),
            R.id.button3 to listOf("Take a nap", "Drink some water"),
            R.id.button4 to listOf("Take deep breaths", "Try some grounding techniques"),
            R.id.button5 to listOf("Take a cold shower", "Go on a walk"),
            R.id.button6 to listOf("Take time to reflect", "Set your priorities")
        )
        // Assigns each button its message
        for ((buttonId, message) in buttonPopups) {
            val button: Button = findViewById(buttonId)
            setupButton(button, message)
        }
        goalButton.setOnClickListener {
            val intent = Intent(this, GoalActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setupButton(button: Button, message: List<String>) {
        button.setOnClickListener {
            animateButton(button) // Button animation when clicked
            popupMessage(message) // Pop-up message
            mmOutStream?.write(message.toString().toByteArray()) // Sends message to bluetooth)

            try {
                val reader = BufferedReader(InputStreamReader(mmInStream))
                val response = reader.readLine()
                Log.i(TAG4, "Response: $response")
            } catch (e: IOException) {
                Log.e(TAG4, "Error reading from input stream")
            }
        }
    }

    private fun animateButton(button: Button) {
        val fadeAnimation = AlphaAnimation(0.5f, 1f).apply {
            duration = 300
            fillAfter = true
        }
        button.startAnimation(fadeAnimation)
    }

    private fun popupMessage(message: List<String>) {
        val formatted = message.joinToString("\n• ", prefix = "• ")
        val dialog = AlertDialog.Builder(this)
            .setTitle("Daily Motivators")
            .setMessage(formatted)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()

        dialog.show()
    }
}
