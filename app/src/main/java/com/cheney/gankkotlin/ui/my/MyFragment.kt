package com.cheney.gankkotlin.ui.my

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.constants.VERSION_NAME
import com.cheney.gankkotlin.databinding.FragmentMyBinding
import com.cheney.gankkotlin.util.TakePhotoObserver
import com.cheney.gankkotlin.util.TakePhotoObserver.Companion.REQUEST_KEY
import com.cheney.gankkotlin.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named


class MyFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<MyViewModel> { factory }

    private var binding by autoCleared<FragmentMyBinding>()

    @Inject
    @Named(VERSION_NAME)
    lateinit var versionName:String

    private lateinit var takePhotoObserver:TakePhotoObserver


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        takePhotoObserver = TakePhotoObserver(this)
        lifecycle.addObserver(takePhotoObserver)

        parentFragmentManager.setFragmentResultListener(REQUEST_KEY,this){_,result ->
            Glide.with(requireContext()).load(result.getParcelable<Bitmap>("avatarBitmap")).into(binding.avatarIv)
        }

        //通过Fragment setFragmentResultListener传递参数
        parentFragmentManager.setFragmentResultListener("userInfo", this) { _, result ->
            var username: String? = result.getString("username")
            username?.let { binding.usernameTv.text = username }
//            if(!username.isNullOrEmpty()){
//                binding.usernameTv.text = username
//            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(inflater, container, false)
        binding.versionTv.text = "1.0.0"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarLayout.toolbar.title = getString(R.string.title_my)

        binding.loginLayout.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_navigation_my_to_loginFragment)
        }

        binding.avatarIv.setOnClickListener { takePhotoObserver.launch() }
    }


}

