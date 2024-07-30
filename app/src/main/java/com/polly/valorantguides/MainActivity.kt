// MainActivity.kt
package com.polly.valorantguides

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.polly.valorantguides.network.RetrofitInstance
import com.polly.valorantguides.model.Agent
import com.polly.valorantguides.model.AgentsResponse
import com.polly.valorantguides.model.Buddy
import com.polly.valorantguides.model.BuddiesResponse
import com.polly.valorantguides.model.Weapon
import com.polly.valorantguides.model.WeaponsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch and display agents
        fetchAgents { agents ->
            recyclerView.adapter = AgentAdapter(agents)
        }

        // Fetch and display buddies
        fetchBuddies { buddies ->
            recyclerView.adapter = BuddyAdapter(buddies)
        }

        // Fetch and display weapons
        fetchWeapons { weapons ->
            recyclerView.adapter = WeaponAdapter(weapons)
        }
    }

    private fun fetchAgents(callback: (List<Agent>) -> Unit) {
        RetrofitInstance.api.getAgents().enqueue(object : Callback<AgentsResponse> {
            override fun onResponse(call: Call<AgentsResponse>, response: Response<AgentsResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load agents", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AgentsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchBuddies(callback: (List<Buddy>) -> Unit) {
        RetrofitInstance.api.getBuddies().enqueue(object : Callback<BuddiesResponse> {
            override fun onResponse(call: Call<BuddiesResponse>, response: Response<BuddiesResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load buddies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BuddiesResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchWeapons(callback: (List<Weapon>) -> Unit) {
        RetrofitInstance.api.getWeapons().enqueue(object : Callback<WeaponsResponse> {
            override fun onResponse(call: Call<WeaponsResponse>, response: Response<WeaponsResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load weapons", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeaponsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
