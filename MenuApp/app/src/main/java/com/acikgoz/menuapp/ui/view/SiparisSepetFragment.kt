package com.acikgoz.menuapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.acikgoz.menuapp.R
import com.acikgoz.menuapp.databinding.FragmentSiparisSepetBinding


class SiparisSepetFragment : Fragment() {

        private lateinit var binding : FragmentSiparisSepetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_siparis_sepet, container, false )


        binding.btnSiparisSepet.setOnClickListener {

            val gecis = SiparisSepetFragmentDirections.siparistenLiseteyeGecis()
            Navigation.findNavController(it).navigate(gecis)
        }

        return binding.root
    }

}