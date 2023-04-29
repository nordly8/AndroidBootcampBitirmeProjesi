package com.acikgoz.menuapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.acikgoz.menuapp.R
import com.acikgoz.menuapp.data.entitiy.Yemekler
import com.acikgoz.menuapp.databinding.FragmentListeBinding
import com.acikgoz.menuapp.ui.adapter.YemekAdapter
import com.acikgoz.menuapp.viewmodel.ListeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListeFragment : Fragment()    {

    private lateinit var binding : FragmentListeBinding
    private lateinit var viewModel : ListeViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_liste, container, false)

        binding.yemekListFragemnt = this
        binding.toolbarListeBaslik ="Ürün Listesi"


        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter = YemekAdapter(requireContext(),it,viewModel)
            binding.yemekAdapter = adapter
            binding.recyclerViewListe.layoutManager = LinearLayoutManager(requireContext())

            adapter.notifyDataSetChanged()
        }

        binding.imageSepetButton.setOnClickListener {

            val gecis = ListeFragmentDirections.listedenSepeteGecis()
            Navigation.findNavController(it).navigate(gecis)
        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : ListeViewModel by viewModels()
        viewModel = tempViewModel

    }



}