package com.lightspeed

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lightspeed.lightspeedproject.R
import com.lightspeed.presentation.ui.FirstActivity
import com.lightspeed.presentation.ui.SecondActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        btn_first_button.setOnClickListener {
            val intentOne = Intent(this@MainActivity, FirstActivity::class.java)
            startActivity(intentOne)
        }

        btn_second_button.setOnClickListener {
            val intentTwo = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intentTwo)
        }
    }
}