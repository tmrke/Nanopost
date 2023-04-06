package ru.ageev.android_homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import ru.ageev.android_homework2.databinding.ActivityMainBinding
import ru.ageev.android_homework2.first_screen.images.ImagesAdapter
import ru.ageev.android_homework2.first_screen.post.PostAdapter
import ru.ageev.android_homework2.first_screen.profile.ProfileAdapter
import ru.ageev.android_homework2.images_screen.ViewImagesCardScreen

class MainActivity : AppCompatActivity() {

    private lateinit var profileAdapter: Adapter
    private lateinit var postAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        val profileAdapter = ProfileAdapter()
        val imagesAdapter = ImagesAdapter()
        val postAdapter = PostAdapter()

        val concatAdapter = ConcatAdapter(profileAdapter, imagesAdapter, postAdapter)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = concatAdapter

        imagesAdapter.buttonImages?.setOnClickListener {
            startActivity(ViewImagesCardScreen.createIntent(this))
        }
    }
}