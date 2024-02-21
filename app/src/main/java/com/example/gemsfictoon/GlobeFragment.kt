package com.example.gemsfictoon

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.gemsfictoon.databinding.FragmentGlobeBinding

class GlobeFragment : Fragment() {

    private lateinit var binding: FragmentGlobeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGlobeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvFirstCVCommunity.setOnClickListener {
            val intent = Intent(activity, GroupNamePublicActivity::class.java)
            startActivity(intent)
        }

        binding.cvSecondCVCommunity.setOnClickListener {
            val intent = Intent(activity, GroupNamePrivateActivity::class.java)
            startActivity(intent)
        }
    }
}