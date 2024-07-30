package com.polly.valorantguides

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.model.Agent
import com.squareup.picasso.Picasso

class AgentAdapter(
    private val agents: List<Agent>,
    private val onItemClick: (Agent) -> Unit
) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agent, parent, false)
        return AgentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = agents[position]
        holder.bind(agent)
    }

    override fun getItemCount(): Int = agents.size

    inner class AgentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val agentName: TextView = itemView.findViewById(R.id.textViewAgentName)
        private val agentIcon: ImageView = itemView.findViewById(R.id.imageViewAgentIcon)

        fun bind(agent: Agent) {
            agentName.text = agent.displayName
            Picasso.get().load(agent.displayIcon).into(agentIcon)
            itemView.setOnClickListener { onItemClick(agent) }
        }
    }
}
