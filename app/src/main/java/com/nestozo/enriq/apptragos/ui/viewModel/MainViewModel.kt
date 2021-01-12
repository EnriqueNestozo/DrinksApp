package com.nestozo.enriq.apptragos.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nestozo.enriq.apptragos.domain.Repo
import com.nestozo.enriq.apptragos.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {
    val fetchDrinkList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getDrinkList())
        }catch (e: Exception){
            //emit(Resource.Failure(e))
        }
    }
}