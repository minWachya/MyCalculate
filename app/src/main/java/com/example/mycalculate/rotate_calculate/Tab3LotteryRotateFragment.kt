package com.example.mycalculate.rotate_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycalculate.databinding.FragmentTab3LotteryRotateBinding
import java.text.NumberFormat
import java.util.*

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

        // <확인> 버튼 눌러서 정산 결과 확인
        // 재고 금액 + 돈 + 당첨 금액 == 이전 근무자 재고 금액?
        binding.btnCheck.setOnClickListener {
            val step1 = binding.step1.getResult().toIntOrNull() ?: 0           // 1. 재고 금액
            val step2 = binding.step2.getResult().toIntOrNull() ?: 0           // 2. 돈 세기
            val step3 = binding.step3.getEditText().toIntOrNull() ?: 0         // 3. 당첨 금액
            val result: Int = step1 + step2 + step3
            val prevResult = binding.step4.getEditText().toIntOrNull() ?: 0    // 4. 이전 근무자 재고 금액

            when {
                // 정산 맞음!
                result == prevResult -> binding.tvResult.text = "짱이다 다 맞아요"
                // 정산 틀림^^: 더 많아요
                result > prevResult -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(result - prevResult).toString()
                    binding.tvResult.text = "헉 $num 원이 많아요"
                }
                // 정산 틀림^^: 더 적어요
                result < prevResult -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(prevResult - result).toString()
                    binding.tvResult.text = "또잉.. $num 원이 없는디요"
                }
                else -> binding.tvResult.text = "뭔가의 오류 발생"
            }
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}