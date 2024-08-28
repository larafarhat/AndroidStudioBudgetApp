package com.example.budgetapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.budgetapp.databinding.FragmentRecordListBinding
import androidx.lifecycle.Observer

class HomeFragment : Fragment() {

    private lateinit var balanceText: TextView
    private  var balance: Float = 0.0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        requireActivity().title = "Home"

        balanceText = view.findViewById<TextView>(R.id.balance)
        balance = arguments?.getFloat("balance") ?: 0.0f
        balanceText.text = "$$balance"


        val addButton = view.findViewById<Button>(R.id.addRecords)
        addButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment3(balance)
            view.findNavController().navigate(action)
        }


        return view
    }
}
