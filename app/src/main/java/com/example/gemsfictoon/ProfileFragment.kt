package com.example.gemsfictoon

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProfileFragment : Fragment() {

    private lateinit var imgButton: Button
    private lateinit var imageView: ImageView

    private var selectedImageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgButton = view.findViewById(R.id.btn_changeProfile)
        imageView = view.findViewById(R.id.iv_image)

        imgButton.setOnClickListener {
            pickImageGallery()
        }

        if (selectedImageUri != null) {
            loadImage(selectedImageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private val pickImageGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                handleImageResult(data)
            }
        }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageGalleryLauncher.launch(intent)
    }

    private fun handleImageResult(data: Intent?) {
        if (data != null && data.data != null) {
            selectedImageUri = data.data
            loadImage(selectedImageUri)
        }
    }

    private fun loadImage(imageUri: Uri?) {
        if (imageUri != null) {
            Glide.with(this)
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
        }
    }

    // Save the current state to handle configuration changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("selectedImageUri", selectedImageUri)
    }

    // Restore the state after configuration changes
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        selectedImageUri = savedInstanceState?.getParcelable("selectedImageUri")
        // Reload the image after restoring the state
        loadImage(selectedImageUri)
    }
}

