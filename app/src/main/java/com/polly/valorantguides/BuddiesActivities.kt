package com.polly.valorantguides

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.polly.valorantguides.databinding.ActivityBuddiesBinding
import com.polly.valorantguides.model.Buddy
import com.polly.valorantguides.model.BuddiesResponse
import com.polly.valorantguides.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuddiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuddiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuddiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewBuddies.layoutManager = LinearLayoutManager(this)

        fetchBuddies { buddies ->
            binding.recyclerViewBuddies.adapter = BuddyAdapter(buddies)
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
