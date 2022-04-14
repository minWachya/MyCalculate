package com.example.mycalculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycalculate.databinding.FragmentTab3LotteryBinding
import java.text.NumberFormat
import java.util.*

private var _binding: FragmentTab3LotteryBinding? = null
private val binding get() = _binding!!

// 3. 복권 정산 순서
class Tab3LotteryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab3LotteryBinding.inflate(inflater, container, false)
        val view = binding.root

        // 8번의 <확인> 버튼 누르면 정산 시작
        // 전 근무자 재고 금액 + 추가 금액 - 현금 판매액 - 당첨 지급액 == 재고 금액인지 확인
        binding.btnCheck.setOnClickListener {
            val prevMoney = binding.edtText.text.toString().toIntOrNull() ?: 0  // 전 근무자 재고 금액
            val plusMoney = binding.step2.getResult().toIntOrNull() ?: 0   // 추가 금액
            val curMoney = binding.step4.getResult().toIntOrNull() ?: 0    // 현금 판매액
            val winMoney = binding.step5.edtText.text.toString().toIntOrNull() ?: 0   // 당복 지급액
            val stockMoney = binding.step3.getResult().toIntOrNull() ?: 0  // 재고 금액

            val result = prevMoney + plusMoney - curMoney - winMoney

            when {
                // 정산 맞음!
                result == stockMoney -> binding.tvResult.text = "참 잘했어용!!"
                // 정산 틀림^^: 재고 금액이 적은 만큼 빈 거임...
                result > stockMoney -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(result - stockMoney).toString()
                    binding.tvResult.text = "민영아... $num 원이 없네...? 괜찮아"
                }
                // 정산 틀림^^: 재고 금액이 많은 만큼 많은 거임...
                result > stockMoney -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(stockMoney - result).toString()
                    binding.tvResult.text = "민영아... $num 원이 많다?"
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