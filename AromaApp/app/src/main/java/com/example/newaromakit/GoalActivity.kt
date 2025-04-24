package com.example.newaromakit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_goal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.goals)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton = findViewById<Button>(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        val affirmations = findViewById<TextView>(R.id.affirmations)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        //List of positive affirmations
        val anxietyAffirmations = listOf(
            "My mind is clear, focused, and free from unnecessary worry.",
            "I can choose to let go of negative thoughts and emotions.",
            "I am in control of how I respond to challenges and setbacks."
        )

        val healthAffirmations = listOf(
            "I am grateful for my vibrant health and well-being.",
            "My body is resilient, and I bounce back to good health easily.",
            "Each day, my health improves in every way."
        )

        val relationshipAffirmations = listOf(
            "I am worthy of love and care.",
            "I receive love in abundance from everyone I meet.",
            "I am at peace, knowing love comes naturally to me."
        )

        val moneyAffirmations = listOf(
            "I always attract success and money in all areas of my working life.",
            "Money often comes to me in wonderful and unexpected ways.",
            "I’m amazed by how rapidly I have enhanced my financial wealth."
        )

        //Randomly choose an affirmation to display
        button1.setOnClickListener {
            affirmations.text = anxietyAffirmations.random()
        }
        button2.setOnClickListener {
            affirmations.text = healthAffirmations.random()
        }
        button3.setOnClickListener {
            affirmations.text = relationshipAffirmations.random()
        }
        button4.setOnClickListener {
            affirmations.text = moneyAffirmations.random()
        }
    }
}