package com.acikgoz.menuapp.data.entitiy

import com.google.gson.annotations.SerializedName

data class SepetlerCevap (@SerializedName("sepet_yemekler") var sepet_yemekler:MutableList<Sepetler>,
                          @SerializedName("success") var success:Int) {
}