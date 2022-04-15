package com.example.mycalculate.rotate_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycalculate.databinding.FragmentTab4TotoRotateBinding

private var _binding: FragmentTab4TotoRotateBinding? = null
private val binding get() = _binding!!

class Tab4TotoRotateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab4TotoRotateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}