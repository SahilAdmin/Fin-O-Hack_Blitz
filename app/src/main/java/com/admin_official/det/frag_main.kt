package com.admin_official.det

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.admin_official.det.databinding.FragMainBinding

class frag_main : Fragment() {

    private lateinit var binding: FragMainBinding
    val viewModel: AppViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragMainBinding.inflate(layoutInflater, null, false)

        viewModel.dailyLimit.observe(requireActivity()) {
            binding.dailyEx.text = it.toString()
        }

        viewModel.monthlyLimit.observe(requireActivity()) {
            binding.monthlyEx.text = it.toString()
        }

        viewModel.yearlyLimit.observe(requireActivity()) {
            binding.yearlyEx.text = it.toString()
        }

        return binding.root
    }

}