package com.example.pavliukovexamples

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class T1DialogFragment : DialogFragment() {

    companion object {
        private const val SHAPE_NAME_KEY = "shape_name_key"
        fun newInstance(userInput: String): T1DialogFragment {
            val fragment = T1DialogFragment()
            val args = Bundle()
            args.putString(SHAPE_NAME_KEY, userInput)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity())
            .setTitle("Shape is")
            .setMessage(arguments?.getString(SHAPE_NAME_KEY) ?: "no message")
            .setPositiveButton("Thanks") { _, _ -> }
            .create()
}