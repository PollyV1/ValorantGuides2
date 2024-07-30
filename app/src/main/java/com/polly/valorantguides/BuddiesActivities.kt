package com.polly.valorantguides

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.network.RetrofitInstance
import com.polly.valorantguides.model.Buddy
import com.polly.valorantguides.model.BuddiesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.squareup.picasso.Picasso
import android.app.AlertDialog
import android.widget.Toast

class BuddiesActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buddies)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBuddies)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchBuddies { buddies ->
            recyclerView.adapter = BuddyAdapter(buddies) { buddy ->
                showBuddyDetails(buddy)
            }
        }
    }

    private fun fetchBuddies(callback: (List<Buddy>) -> Unit) {
        RetrofitInstance.api.getBuddies().enqueue(object : Callback<BuddiesResponse> {
            override fun onResponse(call: Call<BuddiesResponse>, response: Response<BuddiesResponse>) {
                if (response.isSuccessful) {
                    callback(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(this@BuddiesActivities, "Failed to load buddies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BuddiesResponse>, t: Throwable) {
                Toast.makeText(this@BuddiesActivities, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showBuddyDetails(buddy: Buddy) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_buddy_details, null)
        val nameTextView = dialogView.findViewById<TextView>(R.id.textViewBuddyName)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageViewBuddyIcon)

        nameTextView.text = buddy.displayName
        Picasso.get().load(buddy.displayIcon).into(imageView)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
