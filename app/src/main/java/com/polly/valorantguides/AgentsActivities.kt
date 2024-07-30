// AgentsActivity.kt
package com.polly.valorantguides

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.model.Agent
import com.polly.valorantguides.model.AgentsResponse
import com.polly.valorantguides.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agents)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAgents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchAgents { agents ->
            recyclerView.adapter = AgentAdapter(agents)
        }
    }

    private fun fetchAgents(callback: (List<Agent>) -> Unit) {
        RetrofitInstance.api.getAgents().enqueue(object : Callback<AgentsResponse> {
            override fun onResponse(call: Call<AgentsResponse>, response: Response<AgentsResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@AgentsActivity, "Failed to load agents", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AgentsResponse>, t: Throwable) {
                Toast.makeText(this@AgentsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
