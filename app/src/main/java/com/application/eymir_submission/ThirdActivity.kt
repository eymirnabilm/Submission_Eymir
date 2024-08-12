package com.application.eymir_submission

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_listname)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    userResponse?.let {
                        val users = it.data
                        val adapter = UserAdapter(users) { selectedUser ->
                            val resultIntent = Intent()
                            resultIntent.putExtra("selectedUserName", selectedUser.first_name)
                            setResult(Activity.RESULT_OK, resultIntent)
                            finish()
                        }
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
class UserAdapter(
    private val users: List<User>,
    private val onUserSelected: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: CircleImageView = view.findViewById(R.id.userIv)
        val firstNameTv: TextView = view.findViewById(R.id.firstnameTv)
        val lastNameTv: TextView = view.findViewById(R.id.lastnameTv)
        val emailTv: TextView = view.findViewById(R.id.emailTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .into(holder.image)

        holder.firstNameTv.text = user.first_name
        holder.lastNameTv.text = user.last_name
        holder.emailTv.text = user.email

        holder.itemView.setOnClickListener {
            onUserSelected(user)
        }
    }

    override fun getItemCount() = users.size
}