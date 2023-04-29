package com.acikgoz.menuapp.viewmodel


import androidx.lifecycle.ViewModel
import com.acikgoz.menuapp.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetayViewModel  @Inject constructor (var yrepo : YemeklerRepository) : ViewModel() {

    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String) {

        yrepo.yemekSepeteEkle(yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi)
    }
}