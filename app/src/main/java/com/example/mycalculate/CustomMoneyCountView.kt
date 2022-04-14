package com.example.mycalculate

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import java.text.NumberFormat
import java.util.*

// 돈 세기 위젯(순서+내용+EditText들+정산 결과+체크박스)
class CustomMoneyCountView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var tvNum: TextView         // 순서
    lateinit var tvText: TextView        // 내용
    lateinit var edt50000: EditText      // 50,000원 갯수 입력창
    lateinit var edt10000: EditText      // 10,000원 갯수 입력창
    lateinit var edt5000: EditText       // 5,000원 갯수 입력창
    lateinit var edt1000: EditText       // 1.000원 갯수 입력창
    lateinit var edtElse: EditText       // 동전 입력창
    lateinit var tvResult: TextView      // 정산 결과
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
        val view = LayoutInflater.from(context).inflate(R.layout.custom_money_count_view,this,false)
        addView(view)
        tvNum = view.findViewById(R.id.tvNum)
        tvText = view.findViewById(R.id.tvText)
        edt50000 = view.findViewById(R.id.edt50000)
        edt10000 = view.findViewById(R.id.edt10000)
        edt5000 = view.findViewById(R.id.edt5000)
        edt1000 = view.findViewById(R.id.edt1000)
        edtElse = view.findViewById(R.id.edtElse)
        tvResult = view.findViewById(R.id.tvCalculateResult)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMoneyCountView)
        setTypeArray(typedArray)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray) {
        // 순서: CustomMoneyCountView 이름으로 만든 attrs.xml 속성중 num_money_count 참조
        val num = typedArray.getText(R.styleable.CustomMoneyCountView_num_money_count)
        tvNum.text = num

        // 내용: CustomMoneyCountView 이름으로 만든 attrs.xml 속성중 text_money_count 참조
        val text = typedArray.getText(R.styleable.CustomMoneyCountView_text_money_count)
        tvText.text = text

        typedArray.recycle()
    }

    // 리스너 달기: 돈 갯수 입력 시 자동 정산
    private fun setListener() {
        edt50000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edt10000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edt5000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edt1000.doOnTextChanged { _, _, _, _ -> calculateMoney() }
        edtElse.doOnTextChanged { _, _, _, _ -> calculateMoney() }
    }
    // 돈 입력 시 바로 계산하기
    private fun calculateMoney() {
        val count50000 = edt50000.text.toString().toIntOrNull() ?: 0
        val count10000 = edt10000.text.toString().toIntOrNull() ?: 0
        val count5000 = edt5000.text.toString().toIntOrNull() ?: 0
        val count1000 = edt1000.text.toString().toIntOrNull() ?: 0
        val countElse = edtElse.text.toString().toIntOrNull() ?: 0

        val result = 50000*count50000 + 10000*count10000 +
                5000*count5000 + 1000*count1000 + countElse
        tvResult.text = numberFormat.format(result).toString()
    }
}