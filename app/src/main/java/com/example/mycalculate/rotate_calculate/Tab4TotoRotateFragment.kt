package com.example.mycalculate.rotate_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.example.mycalculate.databinding.FragmentTab4TotoRotateBinding
import java.text.NumberFormat
import java.util.*

private var _binding: FragmentTab4TotoRotateBinding? = null
private val binding get() = _binding!!

class Tab4TotoRotateFragment : Fragment() {
    private var numAll = 0      // 전체 판매금
    private var numPrev = 0     // 이전 판매금
    private var result = numAll - numPrev   // 전체 판매금 - 이전 판매금
    // 숫자 3자리마다 , 찍기
    private val numberFormat = NumberFormat.getInstance(Locale.KOREA)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab4TotoRotateBinding.inflate(inflater, container, false)
        val view = binding.root

        // EditText 입력 때마다 바로 결과 계산
        // 1. 내가 뽑은 '현시점 매출 조회'
        binding.edtAll.doOnTextChanged { _, _, _, _ ->      // 전체 현금
            numAll = calculate(binding.edtAll, binding.edtAllRefund, binding.tvCurAllResult1)
            result = numAll - numPrev
            binding.tvNumResult.text = NumberFormat.getInstance(Locale.KOREA).format(result).toString()
        }
        binding.edtAllRefund.doOnTextChanged { _, _, _, _ -> // 전체 환급 + 환불
            numAll = calculate(binding.edtAll, binding.edtAllRefund, binding.tvCurAllResult1)
            result = numAll - numPrev
            binding.tvNumResult.text = NumberFormat.getInstance(Locale.KOREA).format(result).toString()
        }
        // 2. 이전에 뽑은 '현시점 매출 조회'
        binding.edtAllPrev.doOnTextChanged { _, _, _, _ ->  // 이전 전체 현금
            numPrev = calculate(binding.edtAllPrev, binding.edtAllRefundPrev, binding.tvCurAllResult2)
            result = numAll - numPrev
            binding.tvNumResult.text = NumberFormat.getInstance(Locale.KOREA).format(result).toString()
        }
        binding.edtAllRefundPrev.doOnTextChanged { _, _, _, _ ->     // 이전 전체 환급 + 환불
            numPrev = calculate(binding.edtAllPrev, binding.edtAllRefundPrev, binding.tvCurAllResult2)
            result = numAll - numPrev
            binding.tvNumResult.text = NumberFormat.getInstance(Locale.KOREA).format(result).toString()
        }

        // 3. 정산 결과!
        binding.btnCheck.setOnClickListener {
            val money = binding.step4.getResult().toIntOrNull() ?: 0
            when {
                // 정산 맞음!
                result == money -> binding.tvResult.text = "짱이다 다 맞아요"
                // 정산 틀림^^: 더 많아요
                result < money -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(money - result).toString()
                    binding.tvResult.text = "헉 $num 원이 많아요"
                }
                // 정산 틀림^^: 더 적어요
                result > money -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(result - money).toString()
                    binding.tvResult.text = "또잉.. $num 원이 없는디요"
                }
                else -> binding.tvResult.text = "뭔가의 오류 발생"
            }
        }

        return view
    }

    // 판매금 구하기: 전페 현금 - (전페 환급+환불) // 판매금 반환
    private fun calculate(edtAll: EditText, edtRefund: EditText, resultTextView: TextView): Int {
        val all = edtAll.text.toString().toIntOrNull() ?: 0            // 전체 현금
        // 전체 환급 + 환불: +로 나눠서 더하기
        val refundArr = edtRefund.text.toString().split("+")
        var refund = 0
        for (num in refundArr) refund += (num.toIntOrNull() ?: 0)
        resultTextView.text =  NumberFormat.getInstance(Locale.KOREA).format(all - refund).toString()
        return (all - refund)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}