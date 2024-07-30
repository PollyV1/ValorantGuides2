// BuddiesActivity.kt
package com.polly.valorantguides

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.model.Buddy
import com.polly.valorantguides.model.BuddiesResponse
import com.polly.valorantguides.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuddiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buddies)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBuddies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchBuddies { buddies ->
            recyclerView.adapter = BuddyAdapter(buddies)
        }
    }

    private fun fetchBuddies(callback: (List<Buddy>) -> Unit) {
        RetrofitInstance.api.getBuddies().enqueue(object : Callback<BuddiesResponse> {
            override fun onResponse(call: Call<BuddiesResponse>, response: Response<BuddiesResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@BuddiesActivity, "Failed to load buddies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BuddiesResponse>, t: Throwable) {
                Toast.makeText(this@BuddiesActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
