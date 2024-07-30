package com.polly.valorantguides

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.network.RetrofitInstance
import com.polly.valorantguides.model.Agent
import com.polly.valorantguides.model.AgentsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class AgentsActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agents)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAgents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchAgents { agents ->
            recyclerView.adapter = AgentAdapter(agents) { agent ->
                showAgentDetails(agent)
            }
        }
    }

    private fun fetchAgents(callback: (List<Agent>) -> Unit) {
        RetrofitInstance.api.getAgents().enqueue(object : Callback<AgentsResponse> {
            override fun onResponse(call: Call<AgentsResponse>, response: Response<AgentsResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@AgentsActivities, "Failed to load agents", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AgentsResponse>, t: Throwable) {
                Toast.makeText(this@AgentsActivities, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAgentDetails(agent: Agent) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_agent_details, null)
        val nameTextView = dialogView.findViewById<TextView>(R.id.textViewAgentName)
        val descriptionTextView = dialogView.findViewById<TextView>(R.id.textViewAgentDescription)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageViewAgentIcon)

        nameTextView.text = agent.displayName
        descriptionTextView.text = agent.description
        Picasso.get().load(agent.displayIcon).into(imageView)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
