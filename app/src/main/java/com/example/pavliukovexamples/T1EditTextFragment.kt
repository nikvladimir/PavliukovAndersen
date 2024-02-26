package com.example.pavliukovexamples

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pavliukovexamples.databinding.T1FragmentEditTextBinding


class T1EditTextFragment : Fragment() {
    private lateinit var binding: T1FragmentEditTextBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = T1FragmentEditTextBinding.inflate(layoutInflater)
        binding.editText.toastTextListener { s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show() }

        return binding.root
    }

    private fun EditText.toastTextListener(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}