package ru.ageev.nanopost.ui.profile_screen.my_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ageev.nanopost.data.model.Profile
import ru.ageev.nanopost.databinding.ViewMyProfileCardBinding
import javax.inject.Inject

class MyProfileAdapter @Inject constructor() :
    RecyclerView.Adapter<MyProfileViewHolder>() {

    var onEditClick: (String) -> Unit = {}
    var onSubscribeClick: (String) -> Unit = {}
    var onUnsubscribeClick: (String) -> Unit = {}
    var isMyProfile: Boolean = true
    lateinit var profile: Profile

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileViewHolder {
        val binding =
            ViewMyProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyProfileViewHolder(
            binding,
            onEditClick,
            onSubscribeClick,
            onUnsubscribeClick,
            isMyProfile
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyProfileViewHolder, position: Int) {
        holder.bind(profile)
    }
}