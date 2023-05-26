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

class L1EditTextFragment : Fragment() {
    private lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.l1_fragment_edit_text, container, false)
        editText = view.findViewById(R.id.editText)
        editText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int, count: Int, before: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT).show()
                }
            })
        return view
    }
}