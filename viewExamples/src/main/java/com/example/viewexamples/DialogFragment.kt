package com.example.viewexamples

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragment : DialogFragment() {

    companion object {
        private const val SHAPE_NAME_KEY = "shape_name_key"
        fun newInstance(userInput: String): com.example.viewexamples.DialogFragment {
            val fragment = com.example.viewexamples.DialogFragment()
            val args = Bundle()
            args.putString(SHAPE_NAME_KEY, userInput)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.shape_is))
            .setMessage(arguments?.getString(SHAPE_NAME_KEY) ?: getString(R.string.no_message))
            .setPositiveButton(getString(R.string.thanks)) { _, _ -> }
            .create()
}