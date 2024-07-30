package com.polly.valorantguides

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.model.Weapon
import com.polly.valorantguides.model.WeaponsResponse
import com.polly.valorantguides.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.squareup.picasso.Picasso
import android.app.AlertDialog
import android.view.LayoutInflater

class WeaponsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapons)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewWeapons)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchWeapons { weapons ->
            recyclerView.adapter = WeaponAdapter(weapons) { weapon ->
                showWeaponDetails(weapon as Weapon)
            }
        }
    }

    private fun fetchWeapons(callback: (List<Weapon>) -> Unit) {
        RetrofitInstance.api.getWeapons().enqueue(object : Callback<WeaponsResponse> {
            override fun onResponse(call: Call<WeaponsResponse>, response: Response<WeaponsResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@WeaponsActivity, "Failed to load weapons", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeaponsResponse>, t: Throwable) {
                Toast.makeText(this@WeaponsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showWeaponDetails(weapon: Weapon) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_weapon_details, null)
        val nameTextView = dialogView.findViewById<TextView>(R.id.textViewWeaponName)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageViewWeaponIcon)

        nameTextView.text = weapon.displayName
        Picasso.get().load(weapon.displayIcon).into(imageView)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
