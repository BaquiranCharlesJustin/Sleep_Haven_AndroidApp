package com.example.sleep_haven_prototype_legitnato

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var user: FirebaseUser
    private lateinit var auth: FirebaseAuth
    private lateinit var buttonLogout: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

        buttonLogout = findViewById(R.id.logoutButton)
        textView = findViewById(R.id.user_details)

        if (user == null) {
            Log.d("MainActivity", "User is null. Redirecting to Login activity.")
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
            finish()
        } else {
            textView.text = user.email
        }

        buttonLogout.setOnClickListener {
            Log.d("MainActivity", "Logout button clicked.")
            auth.signOut()
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
