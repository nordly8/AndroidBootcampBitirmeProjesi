package com.acikgoz.menuapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.acikgoz.menuapp.data.entitiy.*
import com.acikgoz.menuapp.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerRepository (var yDao : YemeklerDao) {

    var yemeklerListesi : MutableLiveData<List<Yemekler>>
    var sepetListesi : MutableLiveData<MutableList<Sepetler>>


    init {
        yemeklerListesi = MutableLiveData()
        sepetListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }


    fun sepetiGetir() : MutableLiveData<MutableList<Sepetler>> {
        return sepetListesi
    }

    fun yemekSepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        yDao.yemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(object :
            Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message
                Log.e("Sepete Yemek ekle", "$basari - $mesaj")

            }
            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }

    fun tumYemekleriAl(){
        yDao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val list = response.body()!!.yemekler
                          // response.body()!!.success
                yemeklerListesi.value = list

            }
            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }


    fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi:String){
        yDao.sepettenYemekSil(sepet_yemek_id,"kasim_acikgoz").enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message
                Log.e("Sepetten yemek sil", "$basari - $mesaj")
                tumSepetiGoster()
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }

    fun tumSepetiGoster(){
        yDao.sepettekiYemekler("kasim_acikgoz").enqueue(object:Callback<SepetlerCevap>{
            override fun onResponse(call: Call<SepetlerCevap>?, response: Response<SepetlerCevap>) {
                val liste = response.body()!!.sepet_yemekler
                sepetListesi.value = liste
            }

            override fun onFailure(call: Call<SepetlerCevap>, t: Throwable) {}
        })
    }



    }
