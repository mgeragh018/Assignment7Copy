package edu.temple.browsr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider


class ControlFragment : Fragment() {
    private val urlViewModel : UrlViewModel by lazy {
        ViewModelProvider(requireActivity())[UrlViewModel::class.java]
    }
    private lateinit var editTextUrl: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageViewGo = view.findViewById<ImageView>(R.id.imageViewGo)
        val imageViewNext = view.findViewById<ImageView>(R.id.imageViewNext)
        val imageViewBack = view.findViewById<ImageView>(R.id.imageViewBack)
        editTextUrl = view.findViewById<EditText>(R.id.editTextUrl)

        imageViewGo.setImageResource(R.drawable.play_arrow)
        imageViewNext.setImageResource(R.drawable.arrow_forward)
        imageViewBack.setImageResource(R.drawable.arrow_back)

        imageViewGo.setOnClickListener {
            val url = editTextUrl.text.toString().trim()
            if (url.isNotEmpty()) {
                urlViewModel.updateUrl(url)
            }
        }
        imageViewBack.setOnClickListener {
            if (urlViewModel.canGoBack()) {
                urlViewModel.navigateBackward()
            }
        }

        imageViewNext.setOnClickListener {
            if (urlViewModel.canGoForward()) {
                urlViewModel.navigateForward()
            }
        }

        //this logic allows me to set the url to clicked url
        urlViewModel.getUrl().observe(viewLifecycleOwner) { url ->
            if (!url.isNullOrEmpty() && editTextUrl.text.toString() != url) {
                editTextUrl.setText(url)
            }
        }
    }

    companion object {

    }
}