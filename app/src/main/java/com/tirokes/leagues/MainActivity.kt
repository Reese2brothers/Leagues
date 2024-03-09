package com.tirokes.leagues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tirokes.leagues.databinding.ActivityMainBinding
import com.tirokes.leagues.fragments.championship.ChampionshipFragment
import com.tirokes.leagues.fragments.one.OneFragment
import com.tirokes.leagues.fragments.premier.PremierFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flMain, PremierFragment())
            .commit()

        binding.llPL.setOnClickListener {
            binding.llPL.alpha = 1f
            binding.llCSL.alpha = 0.8f
            binding.llOL.alpha = 0.8f
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, PremierFragment())
                .commit()
        }
        binding.llCSL.setOnClickListener {
            binding.llCSL.alpha = 1f
            binding.llPL.alpha = 0.8f
            binding.llOL.alpha = 0.8f
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, ChampionshipFragment())
                .commit()
        }
        binding.llOL.setOnClickListener {
            binding.llOL.alpha = 1f
            binding.llPL.alpha = 0.8f
            binding.llCSL.alpha = 0.8f
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMain, OneFragment())
                .commit()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}