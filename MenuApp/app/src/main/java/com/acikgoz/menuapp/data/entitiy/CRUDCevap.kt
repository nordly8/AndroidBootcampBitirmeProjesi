package com.acikgoz.menuapp.data.entitiy

import com.google.gson.annotations.SerializedName

data class CRUDCevap(@SerializedName("success") var success:Int,
                     @SerializedName("message") var message:String) {
}