package com.cheney.gankkotlin.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.cheney.gankkotlin.R

inline fun FragmentActivity.showCustomDialog(setting: CustomDialogFragment.() -> Unit) {
    //避免重复添加的异常 java.lang.IllegalStateException: Fragment already added
    var fragment: CustomDialogFragment? = supportFragmentManager.findFragmentByTag(CustomDialogFragment.TAG) as CustomDialogFragment?
    if(fragment==null ){
        fragment = CustomDialogFragment.newInstance()
        fragment.apply(setting)
    }
//    fragment.isAdded.let {
//        fragment.dismissAllowingStateLoss()
//        Log.d("BaseDialogFragment","remove")
//    }
    if(fragment.isAdded){
        Log.d("BaseDialogFragment","dismissAllowingStateLoss")
        fragment.dismissAllowingStateLoss()
    }

    fragment.show(supportFragmentManager, CustomDialogFragment.TAG)
}

class CustomDialogFragment : BaseDialogFragment() {

    private lateinit var titleTv: TextView
    private lateinit var messageTv: TextView
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button

    private var leftClick: (() -> Unit)? = null

    private var rightClick: (() -> Unit)? = null

    var cancelOutside: Boolean = true

    var title: String? = null
    var message: String? = null
    var leftBtnText: String? = null
    var leftButtonDismissAfterClick = true
    var rightBtnText: String? = null
    var rightButtonDismissAfterClick = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleTv = view.findViewById(R.id.title_tv)
        messageTv = view.findViewById(R.id.message_tv)
        leftButton = view.findViewById(R.id.left_btn)
        rightButton = view.findViewById(R.id.right_btn)

        dialog?.setCanceledOnTouchOutside(cancelOutside)

        title?.let {

            titleTv.visibility = View.VISIBLE
            titleTv.text = it
        }

        message?.let {
            messageTv.visibility = View.VISIBLE
            messageTv.text = it
        }

        leftClick?.let { onClick ->
            leftButton.visibility = View.VISIBLE
            leftButton.text = leftBtnText
            leftButton.setOnClickListener {
                if (leftButtonDismissAfterClick) dismissAllowingStateLoss()
                onClick()
            }
        }

        rightClick?.let { onClick ->
            rightButton.visibility = View.VISIBLE
            rightButton.text = rightBtnText
            rightButton.setOnClickListener {
                if (rightButtonDismissAfterClick) dismissAllowingStateLoss()
                onClick()
            }
        }
    }

    fun leftClick(dismissAfterClick: Boolean = true, leftTxt: String, callback: () -> Unit) {
        leftBtnText = leftTxt;
        leftButtonDismissAfterClick = dismissAfterClick
        leftClick = callback
    }

    fun rightClick(
        dismissAfterClick: Boolean = true,
        rightTxt: String = getString(R.string.dialog_right_txt),
        callback: () -> Unit
    ) {
        rightBtnText = rightTxt
        rightButtonDismissAfterClick = dismissAfterClick
        rightClick = callback
    }


    companion object {

        const val TAG = "CustomDialogFragment"

        fun newInstance(): CustomDialogFragment {
            return CustomDialogFragment()
        }
    }
}