package com.acikgoz.menuapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.acikgoz.menuapp.R
import com.acikgoz.menuapp.data.entitiy.Sepetler
import com.acikgoz.menuapp.databinding.FragmentSepetBinding
import com.acikgoz.menuapp.ui.adapter.SepetAdapter
import com.acikgoz.menuapp.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {

    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel

    var totalPrice = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        binding.sepetFragment = this
        viewModel.sepetListesi.observe(viewLifecycleOwner) {
            val adapter = SepetAdapter(requireContext(), it, viewModel)

            binding.apply {
                if (binding.recyclerViewSepet.adapter != null &&
                    binding.recyclerViewSepet.adapter?.itemCount == null
                ) {

                } else {
                    sepetAdapter = adapter
                    recyclerViewSepet.layoutManager = LinearLayoutManager(requireContext())
                    sepetToplam = viewModel.sepetToplamFiyat()
                    fiyatGuncelle()
                }
            }
        }
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temptViewModel: SepetViewModel by viewModels()
        viewModel = temptViewModel
    }
    fun btnTikla(it: View) {
        val gecis = SepetFragmentDirections.siparisGecis()
        Navigation.findNavController(it).navigate(gecis)
        Snackbar.make(it, "Siparişiniz alındı..", Snackbar.LENGTH_LONG).show()
    }
    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()

    }

    private fun fiyatGuncelle() {
        binding.textViewTotal.text = totalPrice.toString()
    }
}



