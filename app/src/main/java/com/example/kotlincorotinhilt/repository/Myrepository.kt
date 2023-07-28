package com.example.kotlincorotinhilt.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlincorotinhilt.model.LoginRerspons
import com.example.kotlincorotinhilt.result.Networkresult
import com.example.kotlincorotinhilt.services.ApiServices
import javax.inject.Inject

class Myrepository @Inject constructor(private  val apiServices: ApiServices) {

    private  val mutableLiveData=MutableLiveData<Networkresult<LoginRerspons>>()
    val liveData:LiveData<Networkresult<LoginRerspons>> get() = mutableLiveData

    suspend fun Login(email:String,password:String){

        mutableLiveData.postValue(Networkresult.Loading())
        val respons = apiServices.Login(email,password)

        if (respons.isSuccessful){
            mutableLiveData.postValue(Networkresult.Success(respons.body()!!))
        }else{
            mutableLiveData.postValue(Networkresult.Error("Somthing went wrong"))
        }
    }
}