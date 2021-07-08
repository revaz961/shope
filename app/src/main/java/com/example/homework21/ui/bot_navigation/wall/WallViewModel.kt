package com.example.homework21.ui.bot_navigation.wall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.model.Post
import com.example.homework21.network.ResultHandler
import com.example.homework21.repository.post.PostRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(private val postRepo:PostRepositoryImpl) : ViewModel() {
    private val _liveData = MutableLiveData<ResultHandler<List<Post>>>()
    val liveData:LiveData<ResultHandler<List<Post>>> = _liveData

    fun getPost(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _liveData.postValue(ResultHandler.Loading(true))
                getPosts()
            }
        }
    }

    private suspend fun getPosts(){
        _liveData.postValue(postRepo.getPost())
    }
}