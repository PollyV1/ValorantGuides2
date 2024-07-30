import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polly.valorantguides.AgentAdapter
import com.polly.valorantguides.BuddyAdapter
import com.polly.valorantguides.R
import com.polly.valorantguides.WeaponAdapter
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
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch and display agents
        fetchAgents { agents ->
            recyclerView.adapter = AgentAdapter(agents) { agent ->
                showAgentDetails(agent)
            }
        }

        // Fetch and display buddies
        fetchBuddies { buddies ->
            recyclerView.adapter = BuddyAdapter(buddies) { buddy ->
                showBuddyDetails(buddy)
            }
        }

        // Fetch and display weapons
        fetchWeapons { weapons ->
            recyclerView.adapter = WeaponAdapter(weapons) { weapon ->
                showWeaponDetails(weapon)
            }
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

    private fun showBuddyDetails(buddy: Any) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_buddy_details, null)
        val nameTextView = dialogView.findViewById<TextView>(R.id.textViewBuddyName)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageViewBuddyIcon)



        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    @SuppressLint("MissingInflatedId")
    private fun showWeaponDetails(weapon: Any) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_weapon_details, null)
        val nameTextView = dialogView.findViewById<TextView>(R.id.textViewWeaponName)
        val imageView = dialogView.findViewById<ImageView>(R.id.imageViewWeaponIcon)



        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
