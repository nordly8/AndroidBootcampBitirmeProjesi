package com.acikgoz.menuapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acikgoz.menuapp.R
import com.acikgoz.menuapp.data.entitiy.Sepetler
import com.acikgoz.menuapp.databinding.CardviewSepetBinding
import com.acikgoz.menuapp.viewmodel.SepetViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(
    var context: Context,
    var sepetListesi: MutableList<Sepetler>,
    var viewModel: SepetViewModel,
) : RecyclerView.Adapter<SepetAdapter.SepetHolderClass>() {
    inner class SepetHolderClass(binding: CardviewSepetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val binding: CardviewSepetBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetHolderClass {

        val binding: CardviewSepetBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.cardview_sepet,
            parent,
            false
        )
        return SepetHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return if (sepetListesi.isNotEmpty()) {
            sepetListesi.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: SepetHolderClass, position: Int) {
        val k = holder.binding
        val sepet = sepetListesi[position]

        if (sepetListesi.size > 0) {
            k.tvSepetYemekAdi.text = "${sepet.yemek_adi}"
            k.tvSepetYemekFiyat.text = "${sepet.yemek_fiyat * sepet.yemek_siparis_adet} ₺"
            k.tvSepetYemekAdet.text = "${sepet.yemek_siparis_adet}"

            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
            Glide.with(context).load(url).into(k.ivSepetYemekResim)
            k.ivSilResim.setOnClickListener {
                Snackbar.make(it, "${sepet.yemek_adi} sepetten çıkarılsın mı?", Snackbar.LENGTH_LONG
                ).setAction("EVET") {
                        viewModel.sil(sepet.sepet_yemek_id, sepet.kullanici_adi)
                        sepetBosalt(position)

                    }.show()
            }

            k.eksiBtnSepet.setOnClickListener {
                if (sepet.yemek_siparis_adet > 1) {
                    sepet.yemek_siparis_adet--
                    k.tvSepetYemekAdet.text = "${sepet.yemek_siparis_adet}"
                    k.tvSepetYemekFiyat.text = "${sepet.yemek_siparis_adet * sepet.yemek_fiyat} ₺"

                }
            }

            k.artiBtnSepet.setOnClickListener {
                sepet.yemek_siparis_adet++
                k.tvSepetYemekAdet.text = "${sepet.yemek_siparis_adet}"
                k.tvSepetYemekFiyat.text = "${sepet.yemek_siparis_adet * sepet.yemek_fiyat} ₺"
            }

        } else {
            holder.itemView.visibility = View.GONE
        }
    }

    fun sepetBosalt(position: Int) {
        sepetListesi.removeAt(position)
        notifyItemRemoved(position)
        if (sepetListesi.isEmpty()) {

        } else if (position == sepetListesi.size) {
            toplamFiyatiHesapla()
        }
    }

    private fun toplamFiyatiHesapla():String {
        var toplamFiyat = "0"
        for (item in sepetListesi) {
            toplamFiyat += item.yemek_fiyat

        }
        return toplamFiyat
    }


}
