package com.example.gemsfictoon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gemsfictoon.databinding.ActivityMainBinding

class BookFragment : Fragment() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = ActivityMainBinding.inflate(inflater, container, false)

        return binding.root
    }
}
