package ru.ageev.nanopost.ui.creator_post_screen

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.ageev.nanopost.R
import ru.ageev.nanopost.databinding.FragmentCreatorPostBinding
import ru.ageev.nanopost.service.CreatePostService
import ru.ageev.nanopost.ui.insets.Inset

@AndroidEntryPoint
class CreatorPostFragment : Fragment(R.layout.fragment_creator_post) {
    private val binding by viewBinding(FragmentCreatorPostBinding::bind)

    private var imagesUriList: MutableList<Uri> = mutableListOf()
    private val adapter = ImagesCreatorPostAdapter(imagesUriList)


    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                lifecycleScope.launch {
                    val position = imagesUriList.size
                    imagesUriList.add(uri)

                    adapter.onCancelClick = {
                        imagesUriList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                    }

                    adapter.notifyItemInserted(position)

                    binding.recyclerView.adapter = adapter
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var navController = Navigation.findNavController(view)

        Inset.setInsets(binding.root)


        binding.imageButtonAddImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController = Navigation.findNavController(requireView())
            if (navController.currentDestination?.id == R.id.createPostFragment) {
                navController.navigate(R.id.myProfileFragment)
            }
        }

        binding.toolBar.menu.findItem(R.id.createPostMenu).let { icon ->
            icon.setOnMenuItemClickListener {

                requireContext().startService(
                    CreatePostService.newIntent(
                        requireContext(),
                        binding.editText.text.toString(),
                        imagesUriList
                    )
                )

                navController.navigate(R.id.myProfileFragment)

                return@setOnMenuItemClickListener true
            }
        }


        binding.toolBar.setNavigationOnClickListener {
            navController.navigate(R.id.myProfileFragment)
        }
    }
}