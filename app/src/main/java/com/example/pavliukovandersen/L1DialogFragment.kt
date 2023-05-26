package com.example.pavliukovandersen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class L1DialogFragment : DialogFragment() {

    companion object {
        private const val ARG_USER_INPUT = "user_input"

        fun newInstance(userInput: String): L1DialogFragment {
            val fragment = L1DialogFragment()
            val args = Bundle()
            args.putString(ARG_USER_INPUT, userInput)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireActivity())
            .setTitle("My dialog")
            .setMessage(arguments?.getString(ARG_USER_INPUT) ?: "no message")
            .setPositiveButton("okey") { _, _ -> }
            .create()
}