package com.l0122095.ulhaq.tugasppab05

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailFilmActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_FILMNAME = "extra_filmname"
        const val EXTRA_FILMYEAR = "extra_filmyear"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datafilmName : TextView = findViewById(R.id.detailtextView)

        val filmName = intent.getStringExtra(EXTRA_FILMNAME)

        datafilmName.text = filmName
    }
}