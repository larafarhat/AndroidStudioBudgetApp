package com.example.budgetapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.budgetapp.databinding.FragmentRecordListBinding
import androidx.lifecycle.Observer

class ListFragment : Fragment() {


    private var _binding: FragmentRecordListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordListBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().title = "List"
        val application = requireNotNull(this.activity).application
        val dao = RecordDatabase.getInstance(application).recordDao
        val viewModelFactory = RecordViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RecordViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = RecordItemFragment()
        binding.recordsList.adapter = adapter

        viewModel.record.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}