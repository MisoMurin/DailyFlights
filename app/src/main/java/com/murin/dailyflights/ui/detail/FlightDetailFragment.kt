package com.murin.dailyflights.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.murin.dailyflights.databinding.FragmentFlightDetailBinding

class FlightDetailFragment : Fragment() {
    private val kiwiPackageName = "com.skypicker.main"
    private lateinit var binding: FragmentFlightDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightDetailBinding.inflate(inflater, container, false)



        with(binding) {
            flight = arguments?.getParcelable("flight")

            activity?.packageManager?.let { pm ->
                if (pm.getInstalledPackages(0).any { it.packageName == kiwiPackageName }) {

                    ivShowInKiwi.setImageDrawable(pm.getApplicationIcon(kiwiPackageName))
                    llShowInKiwi.visibility = VISIBLE
                    llShowInKiwi.setOnClickListener {
                        val kiwiIntent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.flight?.deepLink ?: ""))
                        startActivity(kiwiIntent)
                    }
                }
            }

        }

        setHasOptionsMenu(true)
        return binding.root
    }
}
