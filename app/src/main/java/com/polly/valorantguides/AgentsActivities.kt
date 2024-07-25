package com.polly.valorantguides

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.polly.valorantguides.databinding.ActivityAgentsBinding
import com.polly.valorantguides.model.Agent
import com.polly.valorantguides.model.AgentsResponse
import com.polly.valorantguides.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewAgents.layoutManager = LinearLayoutManager(this)

        fetchAgents { agents ->
            binding.recyclerViewAgents.adapter = AgentAdapter(agents)
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
