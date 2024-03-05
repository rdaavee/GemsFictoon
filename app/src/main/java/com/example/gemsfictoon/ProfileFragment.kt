package com.example.gemsfictoon

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gemsfictoon.controller.ApiClient
import com.example.gemsfictoon.controller.TokenManager
import com.example.gemsfictoon.databinding.FragmentProfileBinding
import com.example.gemsfictoon.interfaces.UserProfileRequest
import com.example.gemsfictoon.models.ProfileResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    private lateinit var imgButton: Button
    private lateinit var imageView: ImageView
    private lateinit var settingsBtn : ImageView
    private lateinit var username : TextView
    private lateinit var bio_content : TextView
    private lateinit var follower : TextView
    private lateinit var following : TextView
    private lateinit var usertype : TextView




    private var selectedImageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgButton = view.findViewById(R.id.btn_changeProfile)
        imageView = view.findViewById(R.id.iv_image)
        settingsBtn = view.findViewById(R.id.iv_settingsBtn)

        username = view.findViewById(R.id.nickname)
        bio_content = view.findViewById(R.id.bio_content)
        following = view.findViewById(R.id.following)
        follower = view.findViewById(R.id.follower)
        usertype = view.findViewById(R.id.usertype)

        settingsBtn.setOnClickListener {
            val intent = Intent(activity, ProfileSettingsActivity::class.java)
            startActivity(intent)
        }

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
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        userProfile(requireContext())
        return binding.root
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

    private fun userProfile(applicationContext:Context){
        try {
            val token = TokenManager(applicationContext).getToken()

            if(token == null){
                val intent = Intent(applicationContext, ProfileFragment::class.java)
                startActivity(intent)
            }

            val userProfileCall = ApiClient.userProfile.getData("Bearer $token")
            userProfileCall.enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.isSuccessful){
                        val profile:ProfileResponse? = response.body()

                        if(profile != null){
                            var type =""
                            if(profile.usertype == 1){
                                type ="Admin"
                            }else if(profile.usertype == 2){
                                type ="Author"
                            }else{
                                type ="Reader"
                            }

                            username.text = profile.username
                            bio_content.text = profile.biography
                            follower.text = profile.follower.toString()
                            following.text = profile.following.toString()
                            usertype.text = type

                        }

                        Log.d("Response Success","$profile")

                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                        Log.d("Response Success","$t")
                }
            })
        }catch (e: Exception){
            Log.e("UserProfile", "Exception: ${e.message}", e)
        }

    }

}

