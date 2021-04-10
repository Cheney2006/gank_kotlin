package com.cheney.gankkotlin.util

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.cheney.gankkotlin.dialog.showCustomDialog
import java.io.File

class TakePhotoObserver constructor(val target: Fragment) : DefaultLifecycleObserver {

//    private lateinit var launcher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //调用相机
//        launcher= target.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//            if (it) {
//                takePhoto()
//            } else {
//                Log.i("Cheney","permission denied")
////                target.requireActivity().showCustomDialog { }
//            }
//        }

    }

    fun launch() {
        target.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                takePhoto()
            } else {
                Log.i("Cheney", "permission denied")
//                target.requireActivity().showCustomDialog { }
            }
        }.launch(Manifest.permission.CAMERA)
    }

    private fun takePhoto() {
        target.registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            val bundle = Bundle()
            bundle.putParcelable("avatarBitmap", it)
            target.parentFragmentManager.setFragmentResult(REQUEST_KEY, bundle)

        }.launch(null)
    }

    private fun startCamera() {
        val requireContext = target.requireContext()
        val mCameraFile = File(requireContext.filesDir, "IMAGE_FILE_NAME.jpg") //照相机的File对象
        println("mCameraFile=" + mCameraFile.path)

        val intentFromCapture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0及以上

            val uriForFile: Uri = FileProvider.getUriForFile(
                requireContext,
                "${requireContext.packageName}.fileprovider",
                mCameraFile
            )
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile)
            intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
//  mCameraFile -> /storage/emulated/0/Android/data/com.wenjiehe.android_study/files/IMAGE_FILE_NAME.jpg
        } else {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile))
        }
        target.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }.launch(intentFromCapture)
    }

    companion object {
        const val REQUEST_KEY = "avatarKey"
    }
}