package com.example.mycalculate.close_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.mycalculate.databinding.FragmentTab2PosBinding

private var _binding: FragmentTab2PosBinding? = null
private val binding get() = _binding!!

// 2. 포스 정산 순서
/*
    1. 동전 분류</string>
    2. '메뉴' > '마감정산' > 돈입력
    3. 엔터 > 다음 근무자 로그인
    4. #마감 입금# - 50,000 뺀 금액 적기
    5. #계산원 정산 현황#의 '현금매출'과 비교
    6. 돈 빼고 묶기
* */
class Tab2PosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab2PosBinding.inflate(inflater, container, false)
        val view = binding.root

        // #마감 잔액# 입력 시 -50,000 자동 계산
        binding.step4EditCheck.doOnTextChanged { text, _, _, _ ->
            binding.textResultMinus50000.text = (text.toString().toInt() - 50000).toString()
        }

        // <확인> 버튼 누르면 textResult에 정산 결과 보이기
        binding.btnOk.setOnClickListener {
            // #마감 입금# - 50,000 뺀 금액
            val myMoney = binding.textResultMinus50000.text.toString().toInt()
            // '현금매출'
            val resultMoney = binding.step5EditCheck.text.toString().toIntOrNull() ?: 0

            binding.textResult.text = when {
                // 정산 맞음!
                myMoney == resultMoney -> "OK 굿"
                // 정산 틀림^^: 더 많음
                myMoney > resultMoney -> "${myMoney - resultMoney}원 만큼 많음;;"
                // 정산 틀림^^: 돈이 없네^^
                myMoney < resultMoney -> "${resultMoney - myMoney}원 만큼 없네용"
                else -> "뭔가의 오류"
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}