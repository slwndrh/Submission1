package com.example.submission1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(val list: List<ItemsItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val userName: TextView = view.findViewById(R.id.tv_username)
        val imgVw: ImageView = view.findViewById(R.id.iv_user)
    }

    override fun onCreateViewHolder (viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list[position]
        holder.userName.text = user.login

        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .into(holder.imgVw)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USER, user.login)
            holder.itemView.context.startActivity(intent)
        }
    }
}