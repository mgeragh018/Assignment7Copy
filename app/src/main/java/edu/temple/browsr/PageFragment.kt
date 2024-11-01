package edu.temple.browsr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider

class PageFragment : Fragment() {

    private val urlViewModel: UrlViewModel by lazy {
        ViewModelProvider(requireActivity())[UrlViewModel::class.java]
    }
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = view.findViewById(R.id.webView)

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Set a custom WebViewClient
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val clickedUrl = request?.url.toString()
                Log.d("PageFragment", "User clicked URL: $clickedUrl")

                // Update the ViewModel to reflect the new URL for the EditText
                urlViewModel.updateUrl(clickedUrl)

                // Return false to allow the WebView to handle the URL loading
                return false
            }
        }

        // Load the initial URL if available
        urlViewModel.getUrl().observe(viewLifecycleOwner) { url ->
            if (!url.isNullOrEmpty()) {
                webView.loadUrl(url)
            }
        }

        // Handle forward navigation
        fun navigateBack() {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }

        fun navigateForward() {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }
    }

    companion object {

    }
}