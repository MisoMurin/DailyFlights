package com.murin.dailyflights.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.murin.dailyflights.databinding.FragmentFlightDetailBinding

class FlightDetailFragment : Fragment() {
    private lateinit var binding: FragmentFlightDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightDetailBinding.inflate(inflater, container, false)

        binding.flight = arguments?.getParcelable("flight")

        setHasOptionsMenu(true)
        return binding.root
    }
}
