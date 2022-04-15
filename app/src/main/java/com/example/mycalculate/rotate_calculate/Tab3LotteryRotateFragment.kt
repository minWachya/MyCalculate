package com.example.mycalculate.rotate_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycalculate.databinding.FragmentTab3LotteryRotateBinding

private var _binding: FragmentTab3LotteryRotateBinding? = null
private val binding get() = _binding!!

// 3. 복권 교대 정산
class Tab3LotteryRotateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab3LotteryRotateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}