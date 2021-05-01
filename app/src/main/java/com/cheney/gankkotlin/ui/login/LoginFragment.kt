package com.cheney.gankkotlin.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.postDelayed
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.ProgressBean
import com.cheney.gankkotlin.databinding.FragmentLoginBinding
import com.cheney.gankkotlin.dialog.showCustomDialog
import com.cheney.gankkotlin.ui.SessionViewModel
import com.cheney.gankkotlin.util.StatusBarUtil
import com.cheney.gankkotlin.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val sessionViewModel by activityViewModels<SessionViewModel> { factory }

    private val viewModel by viewModels<LoginViewModel> { factory }

    private var binding by autoCleared<FragmentLoginBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,  container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarLayout.toolbar.title = getString(R.string.title_login)
        StatusBarUtil.setStatusBar(requireActivity())
        binding.toolbarLayout.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
        binding.toolbarLayout.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        binding.loginBtn.setOnClickListener {
            login(binding.usernameEt.text.toString(),binding.passwordEt.text.toString())
        }

    }



    fun login(username: String?, password: String?) {

        var str:String="";
//            str.ifBlank {  }
        val activity=requireActivity()

        username?:let {
            val dialogTitle: String = activity.getString(R.string.dialog_title)
            val dialogMessage: String = activity.getString(R.string.dialog_message_username)
            val cancelBtnTxt =activity.getString(R.string.dialog_left_txt)
            val rightTxt =activity.getString(R.string.dialog_right_txt)
            activity.showCustomDialog {
                title = dialogTitle
                message = dialogMessage
                leftClick(leftTxt = cancelBtnTxt) { Toast.makeText(activity, "取消", Toast.LENGTH_SHORT).show() }
                rightClick(rightTxt = rightTxt) { Toast.makeText(activity, "确定", Toast.LENGTH_SHORT).show() }
            }
            return
        }

        if(password.isNullOrBlank()){
            val dialogTitle: String = activity.getString(R.string. dialog_title)
            val dialogMessage: String = activity.getString(R.string.dialog_message_password)
            val cancelBtnTxt =activity.getString(R.string.dialog_left_txt)
            val rightTxt =activity.getString(R.string.dialog_right_txt)
            activity.showCustomDialog {
                title = dialogTitle
                message = dialogMessage
                leftClick(leftTxt = cancelBtnTxt) { Toast.makeText(activity, "取消", Toast.LENGTH_SHORT).show() }
                rightClick(rightTxt = rightTxt) { Toast.makeText(activity, "确定", Toast.LENGTH_SHORT).show() }
            }
            return
        }

        //模拟加载弹窗
        sessionViewModel.progressLiveData.value= ProgressBean.loading(activity.getString(R.string.dialog_login))


        binding.loginBtn.postDelayed(3000){
            sessionViewModel.progressLiveData.value= ProgressBean.finished()
            val result=Bundle()
            result.putString("username",username)
            parentFragmentManager.setFragmentResult("userInfo",result)
            findNavController().navigateUp()
        }
    }
}