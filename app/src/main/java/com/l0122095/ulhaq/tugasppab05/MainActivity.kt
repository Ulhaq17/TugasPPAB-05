package com.l0122095.ulhaq.tugasppab05

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvFilms = findViewById(R.id.rv_films)
        rvFilms.setHasFixedSize(true)

        list.addAll(getListFilms())
        showRecyclerList()
    }

    private fun getListFilms() : ArrayList<Film> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val dataDuration = resources.getStringArray(R.array.data_duration)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val listHero = ArrayList<Film>()
        for (i in dataName.indices) {
            val hero = Film(dataName[i], dataYear[i], dataImg.getResourceId(i, -1), dataDuration[i], dataDesc[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvFilms.layoutManager = LinearLayoutManager(this)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilms.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickCallback(object : ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                showSelectedFilm(data)
            }

        })
    }

    private fun showSelectedFilm(film: Film) {
        val profileIntent = Intent(this@MainActivity, DetailFilmActivity::class.java)
        profileIntent.putExtra(DetailFilmActivity.EXTRA_FILMNAME, film.name)
        profileIntent.putExtra(DetailFilmActivity.EXTRA_FILMIMG, film.img)
        profileIntent.putExtra(DetailFilmActivity.EXTRA_FILMYEAR, film.year)
        profileIntent.putExtra(DetailFilmActivity.EXTRA_FILMDURATION, film.duration)
        profileIntent.putExtra(DetailFilmActivity.EXTRA_FILMDESC, film.desc)
        startActivity(profileIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFilms.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvFilms.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}