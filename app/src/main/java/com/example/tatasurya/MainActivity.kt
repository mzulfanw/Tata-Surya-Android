package com.example.tatasurya

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tatasurya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Planet>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPlanet.setHasFixedSize(true)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        list.addAll(getListPlanets())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                val person = Person(
                    "Muhammad Zulfan Wahyudin",
                    "mzulfan303@gmail.com",
                    R.drawable.ic_me
                )
                val moveAboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                moveAboutIntent.putExtra(AboutActivity.EXTRA_PERSON, person)
                startActivity(moveAboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListPlanets(): ArrayList<Planet> {
        val dataName = resources.getStringArray(R.array.planet_names)
        val dataDescription = resources.getStringArray(R.array.planet_descriptions)
        val dataPhoto = resources.getStringArray(R.array.planet_photos)
        val listPlanet = ArrayList<Planet>()
        for(i in dataName.indices) {
            val planet = Planet(dataName[i], dataDescription[i], dataPhoto[i])
            listPlanet.add(planet)
        }
        return listPlanet
    }

    private fun showRecyclerList() {
        binding.rvPlanet.layoutManager = LinearLayoutManager(this)
        val listPlanetAdapter = ListPlanetAdapter(list){planet ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_PLANET, planet)
            startActivity(intent)
        }
        binding.rvPlanet.adapter = listPlanetAdapter
    }
}