package com.example.mycalculate.rotate_calculate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.mycalculate.databinding.FragmentTab2PosRotateBinding
import java.text.NumberFormat
import java.util.*

private var _binding: FragmentTab2PosRotateBinding? = null
private val binding get() = _binding!!

// 2. 포스 교대 정산 화면
class Tab2PosRotateFragment : Fragment() {
    private var resultLogin = 0               // 로그인 후 현금값 정산 결과
    private var resultMoney = 0               // 돈 센 결과
    // 숫자 3자리마다 , 찍기
    private val numberFormat = NumberFormat.getInstance(Locale.KOREA)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTab2PosRotateBinding.inflate(inflater, container, false)
        val view = binding.root

        // 1. 로그인 후 현급 값 입력 시 자동 계산
        binding.btnOk.setOnClickListener { evalPlus50000() }

        // 2. 돈 입력 시 바로 계산
        binding.edt50000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt10000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt5000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt1000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt500.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt100.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt50.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        binding.edt10.doOnTextChanged { _, _, _, _ -> calculateMoney() }

        // 3. 확인~
        binding.btnCheck.setOnClickListener {
            when {
                // 정산 맞음!
                resultLogin == resultMoney -> binding.tvResult.text = "짱이다 다 맞아요"
                // 정산 틀림^^: 재고 금액이 적은 만큼 빈 거임...
                resultLogin > resultMoney -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(resultLogin - resultMoney).toString()
                    binding.tvResult.text = "또잉.. $num 원이 없는디요"
                }
                // 정산 틀림^^: 재고 금액이 많은 만큼 많은 거임...
                resultLogin < resultMoney -> {
                    val num = NumberFormat.getInstance(Locale.KOREA).format(resultMoney - resultLogin).toString()
                    binding.tvResult.text = "헉 $num 원이 많아요"
                }
                else -> binding.tvResult.text = "뭔가의 오류 발생"
            }
        }

        return view
    }

    // 1. 로그인 후 현금 값 계산 + 50,000
    private fun evalPlus50000() {
        // '+' 문자로 숫자 나누기
        val arr = binding.step1.text.toString().split("+")
        var sum = 0
        for(num in arr) sum += (num.toIntOrNull() ?: 0)
        resultLogin = sum + 50000
        binding.tvCalculateResult1.text = numberFormat.format(resultLogin).toString()
    }

    // 2. 돈 입력 시 바로 계산하기
    private fun calculateMoney() {
        val count50000 = binding.edt50000.text.toString().toIntOrNull() ?: 0
        val count10000 = binding.edt10000.text.toString().toIntOrNull() ?: 0
        val count5000 = binding.edt5000.text.toString().toIntOrNull() ?: 0
        val count1000 = binding.edt1000.text.toString().toIntOrNull() ?: 0
        val count500 = binding.edt500.text.toString().toIntOrNull() ?: 0
        val count100 = binding.edt100.text.toString().toIntOrNull() ?: 0
        val count50 = binding.edt50.text.toString().toIntOrNull() ?: 0
        val count10 = binding.edt10.text.toString().toIntOrNull() ?: 0

        resultMoney = 50000*count50000 + 10000*count10000 + 5000*count5000 +
                1000*count1000 + 500*count500 + 100*count100 + 50*count50 + 10*count10
        binding.tvCalculateResult2.text = numberFormat.format(resultMoney).toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}