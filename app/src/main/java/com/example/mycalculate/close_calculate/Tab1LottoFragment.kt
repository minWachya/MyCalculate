package com.example.mycalculate.close_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycalculate.databinding.FragmentTab1LottoBinding
import java.text.NumberFormat
import java.util.*

private var _binding: FragmentTab1LottoBinding? = null
private val binding get() = _binding!!

// 1. 로또 정산 순서
/*
    1. 돈 세기
    2. 보고서 > 영업 보고서 > 2장 인쇄
    2-1. 1장 보관
    2-2. 1장 여백에 돈 센 거 적기
    3. 거래내역 적기(확인용)
    4. 거래내역 삭제
    5. 돈 빼고 묶기
    6. 로또인수인계대장 + 야간 체크
*/
class Tab1LottoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab1LottoBinding.inflate(inflater, container, false)
        val view = binding.root

        // <확인> 버튼 누르면 textResult 정산 결과 보이기
        binding.btnOk.setOnClickListener {
            // 야간 판매
            val sale = binding.step6EditSale.text.toString().toIntOrNull() ?: 0
            // 야간 잔액
            val balance = evalPlus()
            // 내가 센 돈 - 야간 판매
            val myMoney = binding.step1.getResult().toInt()
            val myResult = myMoney - sale
            // 거래내역 - 야간 판매
            val resultMoney = (binding.step3EditCheck.text.toString().toIntOrNull() ?: 0) + balance

            binding.textResult.text = when {
                // 정산 맞음!
                myResult == resultMoney -> "OK 굿"
                // 정산 틀림^^: 더 많음
                myResult > resultMoney -> "${myResult - resultMoney}원 만큼 많음;;"
                // 정산 틀림^^: 돈이 없네^^
                myResult < resultMoney -> "${resultMoney - myResult}원 만큼 없네용"
                else -> "뭔가의 오류"
            }
        }

        return view
    }

    // 야간 잔액 계싼
    private fun evalPlus(): Int {
        // '+' 문자로 숫자 나누기
        val arr = binding.step6EditBalance.text.toString().split("+")
        var sum = 0
        for(num in arr) sum += (num.toIntOrNull() ?: 0)

        return sum
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}