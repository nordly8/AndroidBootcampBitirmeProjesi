package com.acikgoz.menuapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.acikgoz.menuapp.R
import com.acikgoz.menuapp.databinding.FragmentDetayBinding
import com.acikgoz.menuapp.viewmodel.DetayViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.ViewModelLifecycle


@AndroidEntryPoint
class DetayFragment : Fragment() {

    private lateinit var binding : FragmentDetayBinding
    private lateinit var viewModel : DetayViewModel

    var adet = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_detay, container, false )

        binding.yemekDetayFragment = this

        val bundle : DetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(this).load(url).into(binding.ivYemekGorsel)

        binding.tvYemekAdi.text = gelenYemek.yemek_adi
        binding.tvYemekFiyat.text = "${gelenYemek.yemek_fiyat} ₺"
        binding.tvYemekAdet.text = "${adet}"

        binding.btnEksi.setOnClickListener {
            if(adet>1){
                adet--
                binding.tvYemekAdet.text = "${adet}"
                binding.tvYemekFiyat.text = "${adet * gelenYemek.yemek_fiyat} ₺"
            }
        }

        binding.btnArti.setOnClickListener {
            adet++
            binding.tvYemekAdet.text = "${adet}"
            binding.tvYemekFiyat.text = "${adet * gelenYemek.yemek_fiyat} ₺"
        }

        binding.btnSepeteEkle.setOnClickListener {
            buttonSepeteEkleTikla(gelenYemek.yemek_adi, gelenYemek.yemek_resim_adi, gelenYemek.yemek_fiyat, adet,"kasim_acikgoz")

            val sepeteGecis = DetayFragmentDirections.sepeteGecis()
            Navigation.findNavController(it).navigate(sepeteGecis)
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temptViewModel : DetayViewModel by viewModels()
        viewModel = temptViewModel
    }

    fun buttonSepeteEkleTikla(yemek_adi:String,
                              yemek_resim_adi:String,
                              yemek_fiyat:Int,
                              yemek_siparis_adet:Int,
                              kullanici_adi:String)  {
        viewModel.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }
}