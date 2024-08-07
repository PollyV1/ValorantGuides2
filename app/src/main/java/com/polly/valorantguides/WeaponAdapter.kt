package com.polly.valorantguides

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.databinding.WeaponItemBinding
import com.polly.valorantguides.model.Weapon
import com.squareup.picasso.Picasso

class WeaponAdapter(private val weapons: List<Weapon>) :
    RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val binding = WeaponItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon = weapons[position]
        holder.bind(weapon)
    }

    override fun getItemCount(): Int = weapons.size

    class WeaponViewHolder(private val binding: WeaponItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weapon: Weapon) {
            binding.weaponName.text = weapon.displayName
            Picasso.get().load(weapon.displayIcon).into(binding.weaponIcon)
        }
    }
}
