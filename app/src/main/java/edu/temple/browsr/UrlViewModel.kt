package edu.temple.browsr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Stack

class UrlViewModel : ViewModel() {

    private val urlLiveData = MutableLiveData<String>()
    var hasSeenSelection = false
    private val backStack = Stack<String>()
    private val forwardStack = Stack<String>()


    fun getUrl(): LiveData<String> = urlLiveData

    fun updateUrl(newUrl: String) {

        urlLiveData.value?.let {
            backStack.push(it)
        }

        forwardStack.clear()


        urlLiveData.value = newUrl
        hasSeenSelection = false
    }

    fun navigateBackward() {
        if (backStack.isNotEmpty()) {

            val previousUrl = backStack.pop()
            urlLiveData.value?.let {
                forwardStack.push(it)
            }

            urlLiveData.value = previousUrl
        }
    }

    fun navigateForward() {
        if (forwardStack.isNotEmpty()) {

            val nextUrl = forwardStack.pop()
            urlLiveData.value?.let {
                backStack.push(it)
            }
            // Update LiveData with next URL
            urlLiveData.value = nextUrl
        }
    }

    // Check if back navigation is possible
    fun canGoBack(): Boolean = backStack.isNotEmpty()

    // Check if forward navigation is possible
    fun canGoForward(): Boolean = forwardStack.isNotEmpty()
}