package com.example.tesdot.ui

import android.os.Parcel
import android.os.Parcelable
import android.telecom.Call
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.tesdot.model.Wisata
import com.example.tesdot.network.NetworkConfig
import okhttp3.Response
import javax.security.auth.callback.Callback

class WisataViewModel() : ViewModel() {

    private var data = MutableLiveData<Wisata>()
    private var status = MutableLiveData<Boolean>()

    init {

        getData()
    }

    private fun getData() {

        status.value = true

        NetworkConfig().api().wisata().enqueue(object : Callback<Wisata> {
            override fun onFailure(call: Call<Wisata>, t: Throwable) {
                status.value = true
                data.value = null
            }

            override fun onResponse(call: Call<Wisata>, response: Response<Wisata>) {
                status.value = false

                if(response.isSuccessful){
                    data.value = response.body()
                }
                else{
                    status.value = true
                }



            }
        })
    }

    fun setData() : MutableLiveData<Wisata> {

        return data
    }

    fun getStatus(): MutableLiveData<Boolean> {

        status.value = true

        return status


    }

}

