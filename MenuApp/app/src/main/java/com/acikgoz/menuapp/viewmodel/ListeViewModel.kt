package com.acikgoz.menuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acikgoz.menuapp.data.entitiy.Yemekler
import com.acikgoz.menuapp.data.repo.YemeklerRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListeViewModel @Inject constructor(var yRepo : YemeklerRepository)  : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
        init {
            yemekleriYukle()
            yemeklerListesi = yRepo.yemekleriGetir()
        }

    fun yemekleriYukle(){
        yRepo.tumYemekleriAl()
    }

}