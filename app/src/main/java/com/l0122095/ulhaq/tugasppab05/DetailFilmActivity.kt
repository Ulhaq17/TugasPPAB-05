package com.l0122095.ulhaq.tugasppab05

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailFilmActivity : AppCompatActivity() {

    private lateinit var btnTrailer : Button

    companion object{
        const val EXTRA_FILMNAME = "extra_filmname"
        const val EXTRA_FILMIMG = "extra_filmimg"
        const val EXTRA_FILMYEAR = "extra_filmyear"
        const val EXTRA_FILMDURATION = "extra_filmduration"
        const val EXTRA_FILMDESC = "extra_filmdesc"
        const val EXTRA_FILMLINK = "extra_filmlink"
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
        val datafilmImg : ImageView = findViewById(R.id.detailimageView)
        val datafilmDuration : TextView = findViewById(R.id.detailtextView2)
        val datafilmDesc: TextView = findViewById(R.id.detailtextView4)

        val filmName = intent.getStringExtra(EXTRA_FILMNAME)
        val filmImg = intent.getIntExtra(EXTRA_FILMIMG, 0)
        val filmDuration = intent.getStringExtra(EXTRA_FILMDURATION)
        val filmDesc = intent.getStringExtra(EXTRA_FILMDESC)

        datafilmName.text = filmName
        datafilmDuration.text = filmDuration
        datafilmDesc.text = filmDesc
        datafilmImg.setImageResource(filmImg)

        btnTrailer = findViewById(R.id.button)
        btnTrailer.setOnClickListener {
            val filmLink = intent.getStringExtra(EXTRA_FILMLINK)
            trailerYouTubeLink(filmLink)
        }
    }

    private fun trailerYouTubeLink(youTubeLink: String?) {
        youTubeLink?.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        }
    }
}