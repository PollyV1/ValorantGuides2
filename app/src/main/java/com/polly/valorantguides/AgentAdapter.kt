package com.polly.valorantguides

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.databinding.AgentItemBinding
import com.polly.valorantguides.model.Agent
import com.squareup.picasso.Picasso

class AgentAdapter(private val agents: List<Agent>) :
    RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val binding = AgentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = agents[position]
        holder.bind(agent)
    }

    override fun getItemCount(): Int = agents.size

    class AgentViewHolder(private val binding: AgentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(agent: Agent) {
            binding.agentName.text = agent.displayName
            Picasso.get().load(agent.displayIcon).into(binding.agentIcon)
        }
    }
}
