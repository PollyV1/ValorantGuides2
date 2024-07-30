package com.polly.valorantguides

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.databinding.ItemBuddyBinding
import com.polly.valorantguides.model.Buddy
import com.squareup.picasso.Picasso

class BuddyAdapter(
    private val buddies: List<Buddy>,
    private val onItemClick: (Buddy) -> Unit // Changed from (Any) -> Unit to (Buddy) -> Unit
) : RecyclerView.Adapter<BuddyAdapter.BuddyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuddyViewHolder {
        val binding = ItemBuddyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuddyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuddyViewHolder, position: Int) {
        val buddy = buddies[position]
        holder.bind(buddy)
    }

    override fun getItemCount(): Int = buddies.size

    inner class BuddyViewHolder(private val binding: ItemBuddyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(buddy: Buddy) {
            binding.textViewBuddyName.text = buddy.displayName
            Picasso.get()
                .load(buddy.displayIcon)
                .into(binding.imageViewBuddyIcon)

            // Handle item click
            binding.root.setOnClickListener {
                onItemClick(buddy)
            }
        }
    }
}
