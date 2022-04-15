package com.example.mycalculate.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.example.mycalculate.R
import java.text.NumberFormat
import java.util.*

// Option 위젯(순서+내용+EditText들+정산 타입+정산 결과+체크박스)
class CustomLotteryView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var tvNum: TextView         // 순서
    lateinit var tvText: TextView        // 내용
    lateinit var edt2000Set: EditText    // 2000원 세트 갯수 입력창
    lateinit var edt2000: EditText       // 2000원 낱개 갯수 입력창
    lateinit var edt1000Set: EditText    // 1000원 세트 갯수 입력창
    lateinit var edt1000: EditText       // 1000원 낱개 갯수 입력창
    lateinit var edtYear: EditText       // 연금 갯수 입력 창
    lateinit var tvType: TextView        // 정산 타입
    lateinit var tvResult: TextView      // 정산 결과 TextView
    private var result = 0               // 정산 결과
    // 숫자 3자리마다 , 찍기
    private val numberFormat = NumberFormat.getInstance(Locale.KOREA)

    // 생성자
    constructor(context: Context?) : super(context!!){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs){
        init(context)
        getAttrs(attrs)
        setListener()
    }

    // 초기화
    private fun init(context:Context?) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_lottery_view,this,false)
        addView(view)
        tvNum = view.findViewById(R.id.tvNum)
        tvText = view.findViewById(R.id.tvText)
        edt2000Set = view.findViewById(R.id.edt2000_set)
        edt2000 = view.findViewById(R.id.edt2000)
        edt1000Set = view.findViewById(R.id.edt1000_set)
        edt1000 = view.findViewById(R.id.edt1000)
        edtYear = view.findViewById(R.id.edtYear)
        tvType = view.findViewById(R.id.tvCalculateType)
        tvResult = view.findViewById(R.id.tvCalculateResult)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLotteryView)
        setTypeArray(typedArray)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray) {
        // 순서: CustomLotteryView 이름으로 만든 attrs.xml 속성중 num_lottery 참조
        val num = typedArray.getText(R.styleable.CustomLotteryView_num_lottery)
        tvNum.text = num

        // 내용: CustomLotteryView 이름으로 만든 attrs.xml 속성중 text_lottery 참조
        val text = typedArray.getText(R.styleable.CustomLotteryView_text_lottery)
        tvText.text = text

        // 정산 타입: CustomLotteryView 이름으로 만든 attrs.xml 속성중 option1_text_lottery 참조
        val type = typedArray.getText(R.styleable.CustomLotteryView_type_lottery)
        tvType.text = type

        typedArray.recycle()
    }

    // 리스너 달기: 복권 숫자 입력 시 자동 정산
    private fun setListener() {
        edt2000Set.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edt2000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edt1000Set.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edt1000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edtYear.doOnTextChanged { _, _, _, _ -> calculateMoney() }
    }
    // 돈 입력 시 바로 계산하기
    private fun calculateMoney() {
        val count2000Set = edt2000Set.text.toString().toIntOrNull() ?: 0
        val count2000 = edt2000.text.toString().toIntOrNull() ?: 0
        val count1000Set = edt1000Set.text.toString().toIntOrNull() ?: 0
        val count1000 = edt1000.text.toString().toIntOrNull() ?: 0
        val countYear = edtYear.text.toString().toIntOrNull() ?: 0

        result = 2000*2*count2000Set + 2000*count2000 +
                1000*4*count1000Set + 1000*count1000 + 1000*countYear
        tvResult.text = numberFormat.format(result).toString()
    }

    // 정산 결과 반환
    fun getResult(): String = result.toString()
}