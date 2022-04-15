package com.example.mycalculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.mycalculate.databinding.FragmentTab4TotoBinding
import java.text.NumberFormat
import java.util.*

private var _binding: FragmentTab4TotoBinding? = null
private val binding get() = _binding!!

// 4. 토토 정산 순서
class Tab4TotoFragment : Fragment() {
    // 숫자 3자리마다 , 찍기
    private val numberFormat = NumberFormat.getInstance(Locale.KOREA)
    private var curAllResult = 0        // 현시점 판매금
    private var curRefundResult = 0     // 현시점 환급+환불
    private var allRefundResult = 0           // 전체 환급+환불

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab4TotoBinding.inflate(inflater, container, false)
        val view = binding.root

        // 4단계
        // 4-1, 현시전 판매금 구하기: 전체 판매금 - 이전 전체 판매금
        binding.edtAll.doOnTextChanged { _, _, _, _ ->  calculateCurAll() }
        binding.edtPrevAll.doOnTextChanged { _, _, _, _ ->  calculateCurAll() }

        // 4-2, 전체 환급 + 환불 구하기
        binding.edtRefund1.doOnTextChanged { _, _, _, _ ->  calculateAllRefunds() }
        binding.edtRefund2.doOnTextChanged { _, _, _, _ ->  calculateAllRefunds() }

        // 4-3, 현시점 환급 + 환불 구하기: 전체 환급+환불 - 이전 환급+환불
        binding.edtPrevRefunds.doOnTextChanged { text, _, _, _ ->
            val prevRefund = text.toString().toIntOrNull() ?: 0                     // 이전 환급+환불

            binding.tvCurRefund.text = numberFormat.format(allRefundResult - prevRefund).toString()
            curRefundResult = allRefundResult - prevRefund
        }

        // 정산 결과 구하기: 현시점 판매금 - 현시점 환불+환급 == 돈?
        binding.btnCheck.setOnClickListener {
            val result = curAllResult - curRefundResult
            val money = binding.step1.getResult().toIntOrNull() ?: 0

            when {
                // 정산 맞음!
                result == money -> binding.tvResult.text = "참 잘했어용!!"
                // 정산 틀림^^: 재고 금액이 적은 만큼 빈 거임...
                result > money -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(result - money).toString()
                    binding.tvResult.text = "민영아... $num 원이 없네...? 괜찮아 안죽어!!"
                }
                // 정산 틀림^^: 재고 금액이 많은 만큼 많은 거임...
                result < money -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(money - result).toString()
                    binding.tvResult.text = "민영아 $num 원이 많아~♡"
                }
                else -> binding.tvResult.text = "뭔가의 오류 발생"
            }
        }

        return view
    }

    // 4-1, 현시점 판매금 구하기: 전체 판매금 - 이전 전체 판매금
    private fun calculateCurAll() {
        val all = binding.edtAll.text.toString().toIntOrNull() ?: 0         // 전체 판매금
        val prevAll = binding.edtPrevAll.text.toString().toIntOrNull() ?: 0 // 이전 전체 판매금

        binding.tvCurAllResult.text = numberFormat.format(all - prevAll).toString()
        curAllResult = all - prevAll
    }
    // 4-2, 전체 환급 + 환불 구하기
    private fun calculateAllRefunds() {
        val refund1 = binding.edtRefund1.text.toString().toIntOrNull() ?: 0     // 전체 환급
        val refund2 = binding.edtRefund2.text.toString().toIntOrNull() ?: 0     // 이전 환불

        binding.tvAllRefund.text = numberFormat.format(refund1 + refund2).toString()
        allRefundResult = refund1 + refund2
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}