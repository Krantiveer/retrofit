package com.example.myapplication.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar

/**
 * Created by krantiveer  on 1/11/22 @ 11:52 am
 * Contact - krantiveersinghgaur@gmail.com ► +91-9928552344
 */
object CMHelper {
    /**
     * Use for show the messages
     *
     * @param parentView -> View for Snack Bar
     * @param message    -> Message in details
     * @param status     -> 0 for regular, 1 for Success message and 2 for failure
     */
    fun setSnackBar(parentView: View, message: String?, status: Int) {
        if (message != null) {
            val snackBar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
            val view = snackBar.view
            val textView =
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.typeface = ResourcesCompat.getFont(parentView.context, R.font.amazon)
            textView.maxLines = 5
            textView.ellipsize = TextUtils.TruncateAt.END
            textView.setTextColor(ContextCompat.getColor(parentView.context, android.R.color.white))
            if (status == 1) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.holo_green_dark
                    )
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.white
                    )
                )
            } else if (status == 2) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.holo_red_dark
                    )
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.white
                    )
                )
            }
            snackBar.show()
        }
    }

    /**
     * Use for show the messages
     *
     * @param parentView -> View for Snack Bar
     * @param message    -> Message in details
     * @param status     -> 0 for regular, 1 for Success message and 2 for failure
     * @param length     -> for showing snack bar duration
     */
    fun setSnackBar(parentView: View, message: String?, status: Int, length: Int) {
        if (message != null) {
            val snackBar = Snackbar.make(parentView, message, length)
            val view = snackBar.view
            val textView =
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.typeface = ResourcesCompat.getFont(parentView.context, R.font.amazon)
            textView.maxLines = 5
            textView.ellipsize = TextUtils.TruncateAt.END
            textView.setTextColor(ContextCompat.getColor(parentView.context, android.R.color.white))
            if (status == 1) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.holo_green_dark
                    )
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.white
                    )
                )
            } else if (status == 2) {
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.holo_red_dark
                    )
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        parentView.context,
                        android.R.color.white
                    )
                )
            }
            snackBar.show()
        }
    }

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}