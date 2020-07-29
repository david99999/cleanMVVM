package com.demo.clean.framework.screens.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.clean.R
import com.demo.clean.domain.models.UserShortInfo
import kotlin.properties.Delegates

class UsersAdapter(val userClicked: (userInfo: UserShortInfo) -> Unit) :
    RecyclerView.Adapter<UserInfoVH>() {
    var users: List<UserShortInfo> by Delegates.observable(emptyList(),
        { _, _, _ -> notifyDataSetChanged() })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserInfoVH(view = view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserInfoVH, position: Int) {
        holder.setUser(users[position])
        holder.view.setOnClickListener { userClicked(users[position]) }
    }
}

class UserInfoVH(val view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.user_name)
    val org: TextView = view.findViewById(R.id.user_company)

    fun setUser(userInfo: UserShortInfo) {
        name.text = userInfo.name
        org.text = userInfo.company
    }
}