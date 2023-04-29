package com.acikgoz.menuapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.acikgoz.menuapp.R
import com.acikgoz.menuapp.data.entitiy.Yemekler
import com.acikgoz.menuapp.databinding.CardviewYemekBinding
import com.acikgoz.menuapp.ui.view.ListeFragmentDirections
import com.acikgoz.menuapp.viewmodel.ListeViewModel
import com.bumptech.glide.Glide

class YemekAdapter(
    var context: Context,
    var yemeklerListesi: List<Yemekler>,
    var viewModel: ListeViewModel
) : RecyclerView.Adapter<YemekAdapter.YemekHolderClass>() {

    inner class YemekHolderClass(var binding: CardviewYemekBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            this.binding = binding
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolderClass {

        val binding: CardviewYemekBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.cardview_yemek,
            parent,
            false
        )
        return YemekHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: YemekHolderClass, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val k = holder.binding

        k.tvYemekAd.text = "${yemek.yemek_adi}"
        k.tvYemekFiyat.text = "${yemek.yemek_fiyat}₺"

        k.listeSatirCardView.setOnClickListener {

            val gecis = ListeFragmentDirections.detayaGecis(yemek = yemek)
            Navigation.findNavController(it).navigate(gecis)
        }



        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(context).load(url).into(k.ivYemekGorsel)
    }

}