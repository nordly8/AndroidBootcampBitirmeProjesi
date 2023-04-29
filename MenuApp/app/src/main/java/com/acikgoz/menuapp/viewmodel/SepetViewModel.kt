package com.acikgoz.menuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acikgoz.menuapp.data.entitiy.Sepetler
import com.acikgoz.menuapp.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor (var yrepo : YemeklerRepository) : ViewModel() {

    var sepetListesi = MutableLiveData<MutableList<Sepetler>>()
    var totalPrice = 0
        private set

    init {

        sepetiYukle()
        sepetListesi = yrepo.sepetiGetir()
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        yrepo.sepetYemekSil(sepet_yemek_id,kullanici_adi)
        sepetSonItem()
    }

    fun sepetiYukle(){
        sepetToplamFiyat()
        yrepo.tumSepetiGoster()
        sepetToplamFiyat()
    }

    fun sepetToplamFiyat(): String{
        var toplam = 0
        sepetListesi.value?.forEach {
            toplam += it.yemek_siparis_adet * it.yemek_fiyat

        }
        return toplam.toString()
    }
    fun sepetSonItem() {
        if (!sepetListesi.value.isNullOrEmpty()) {
            val sonItem = sepetListesi.value!!.toMutableList()
            sonItem.removeAt(sonItem.lastIndex)
            sepetListesi.value = sonItem
            totalPrice = sonItem.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
        }
    }


}