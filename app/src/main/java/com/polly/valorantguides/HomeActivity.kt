package com.polly.valorantguides

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.polly.valorantguides.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAgents.setOnClickListener {
            startActivity(Intent(this, AgentsActivities::class.java))
        }

        binding.buttonBuddies.setOnClickListener {
            startActivity(Intent(this, BuddiesActivities::class.java))
        }

        binding.buttonWeapons.setOnClickListener {
            startActivity(Intent(this, WeaponsActivity::class.java))
        }
    }
}
