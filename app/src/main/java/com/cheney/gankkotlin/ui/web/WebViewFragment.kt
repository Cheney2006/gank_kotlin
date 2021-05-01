package com.cheney.gankkotlin.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.databinding.FragmentWebviewBinding
import com.cheney.gankkotlin.util.StatusBarUtil
import com.cheney.gankkotlin.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WebViewFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<WebViewViewModel> { factory }

    private var binding by autoCleared<FragmentWebviewBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progressBar.progress = newProgress
                binding.progressBar.visibility = if (newProgress < 100) View.VISIBLE else View.GONE

            }
        }
        binding.webView.settings.javaScriptEnabled = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        StatusBarUtil.setStatusBar(requireActivity())
        val args = WebViewFragmentArgs.fromBundle(requireArguments())
        val title = args.title
        binding.toolbarLayout.toolbar.title = title
        binding.toolbarLayout.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        binding.toolbarLayout.toolbar.setNavigationOnClickListener { findNavController().navigateUp()  }
        binding.webView.loadUrl(args.url)
    }
}
