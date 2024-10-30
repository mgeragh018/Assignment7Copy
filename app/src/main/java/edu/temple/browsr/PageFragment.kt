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

    private val urlViewModel : UrlViewModel by lazy {
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

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()


        urlViewModel.getUrl().observe(viewLifecycleOwner) { url ->
            if (!url.isNullOrEmpty()) {
                webView.loadUrl(url)
            }
        }
        urlViewModel.getNavigateForward().observe(viewLifecycleOwner) { navigateForward ->
            if (navigateForward && webView.canGoForward()) {
                webView.goForward()
            }
        }


        urlViewModel.getNavigateBackward().observe(viewLifecycleOwner) { navigateBackward ->
            if (navigateBackward && webView.canGoBack()) {
                webView.goBack()
            }
        }
    }

    companion object {

    }
}