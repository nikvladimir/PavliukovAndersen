package com.example.pavliukovandersen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast

class T1EditTextFragment : Fragment() {
    private lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.t1_fragment_edit_text, container, false)
        editText = view.findViewById(R.id.editText)
        editText.toastTextListener { s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show() }
        return view
    }

    private fun EditText.toastTextListener(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable) { afterTextChanged.invoke(editable.toString())
            }
        })
    }
}