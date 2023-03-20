package com.example.labbb2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class NewsletterFragment : Fragment() {

    private val emailList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_newsletter, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val submitEmailButton = view.findViewById<Button>(R.id.submitEmailButton)

        submitEmailButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                emailList.add(email)
                emailEditText.setText("")

                val snackbar = Snackbar.make(
                    view, "Email added: $email",
                    Snackbar.LENGTH_LONG
                ).setAction("UNDO") {
                    emailList.remove(email)
                    Toast.makeText(requireContext(), "Email removed: $email", Toast.LENGTH_SHORT).show()
                }
                snackbar.show()
            } else {
                Toast.makeText(requireContext(), "Please enter a valid email.", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
