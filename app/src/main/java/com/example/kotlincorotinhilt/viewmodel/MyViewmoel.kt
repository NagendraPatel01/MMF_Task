package com.example.kotlincorotinhilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincorotinhilt.model.LoginRerspons
import com.example.kotlincorotinhilt.repository.Myrepository
import com.example.kotlincorotinhilt.result.Networkresult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewmoel @Inject constructor(private val myrepository: Myrepository):ViewModel() {

    val liveData: LiveData<Networkresult<LoginRerspons>> get() = myrepository.liveData

    fun Login(email:String,password:String){

        viewModelScope.launch(Dispatchers.IO) {
           myrepository.Login(email,password)
        }
    }
}