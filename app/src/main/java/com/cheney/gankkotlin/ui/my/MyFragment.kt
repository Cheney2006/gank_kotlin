package com.cheney.gankkotlin.ui.my

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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
import java.io.File
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.eventHandler = EventHandlers(takePhotoObserver,binding)
        binding.versionName="1.0.0"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarLayout.toolbar.title = getString(R.string.title_my)


    }


    class EventHandlers(var takePhotoObserver: TakePhotoObserver,var binding: FragmentMyBinding) {

        fun gotoLogin(view: View) {
            Navigation.findNavController(view).navigate(R.id.action_navigation_my_to_loginFragment)
        }

        fun avatarClick(view: View) {
            takePhotoObserver.launch()
        }



    }


}

