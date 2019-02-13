package com.murin.dailyflights.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.murin.dailyflights.databinding.FragmentFlightsListBinding

class FlightsListFragment : Fragment() {
    private lateinit var binding: FragmentFlightsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightsListBinding.inflate(inflater, container, false)
        return binding.root
    }

}
