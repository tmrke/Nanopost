package ru.ageev.android_homework2.ui.images_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.ageev.android_homework2.R
import ru.ageev.android_homework2.data.ImageData
import ru.ageev.android_homework2.databinding.FragmentImagesBinding
import ru.ageev.android_homework2.ui.insets.Inset
import ru.ageev.android_homework2.ui.profile_screen.profile.ProfileViewModel

@AndroidEntryPoint
class ImagesFragment : Fragment(R.layout.fragment_images) {
    private val binding by viewBinding(FragmentImagesBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        Inset.setInsets(view)

        val dataList = List(30) { ImageData() }
        val listAdapter = ImagesAdapter(dataList)

        binding.recyclerView.adapter = listAdapter

        binding.toolBar.setNavigationOnClickListener {
            navController.navigate(R.id.profileFragment)
        }
    }
}