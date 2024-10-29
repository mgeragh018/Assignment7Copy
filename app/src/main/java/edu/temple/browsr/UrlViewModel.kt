package edu.temple.browsr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UrlViewModel : ViewModel() {

    private val urlLiveData = MutableLiveData<String>()
    var hasSeenSelection = false
    fun getUrl(): LiveData<String> {
        return urlLiveData
    }

    fun updateUrl(newUrl: String) {
        hasSeenSelection = false
        urlLiveData.value = newUrl
    }

}