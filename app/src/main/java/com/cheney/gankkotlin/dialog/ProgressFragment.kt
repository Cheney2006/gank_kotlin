package com.cheney.gankkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.cheney.gankkotlin.R

class ProgressFragment constructor(private val message: String, private val cancelable: Boolean=true) : BaseDialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         var dialog= ProgressDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(cancelable)
            .create()
        dialog.show()
        return dialog
    }

    class ProgressDialog : Dialog {

        private var messageTv: TextView? = null

        constructor(context: Context) : super(context) {
            initView()
        }

        constructor(context: Context, themeResId: Int) : super(context, themeResId) {
            initView()
        }

        private fun initView() {
            setContentView(R.layout.dialog_progress)
            messageTv = findViewById(R.id.message_tv)
        }

        class Builder(val context: Context) {
            private var message: String? = null
            private var cancelable: Boolean = true

            fun setMessage(message: String): Builder {
                this.message = message;
                return this
            }

            fun setCancelable(cancelable: Boolean): Builder {
                this.cancelable = cancelable
                return this
            }

            fun create(): ProgressDialog {
                val dialog = ProgressDialog(context)
                dialog.setCancelable(cancelable)
                message?.let {
                    dialog.messageTv?.text = it
                }
                return dialog
            }
        }

    }

    companion object{
        const val TAG="ProgressFragment"
    }
}

