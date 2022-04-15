package com.example.mycalculate.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mycalculate.R

// Option 위젯(순서+내용+option1+option2+체크박스)
class CustomOptionView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var tvNum: TextView         // 순서
    lateinit var tvText: TextView        // 내용
    lateinit var tvOption1: TextView     // option1
    lateinit var tvOption2: TextView     // option2

    // 생성자
    constructor(context: Context?) : super(context!!){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs){
        init(context)
        getAttrs(attrs)
    }

    // 초기화
    private fun init(context:Context?) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_option_view,this,false)
        addView(view)
        tvNum = view.findViewById(R.id.tvNum)
        tvText = view.findViewById(R.id.tvText)
        tvOption1 = view.findViewById(R.id.tvOption1)
        tvOption2 = view.findViewById(R.id.tvOption2)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomOptionView)
        setTypeArray(typedArray)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray) {
        // 순서: CustomOptionView 이름으로 만든 attrs.xml 속성중 num_option 참조
        val num = typedArray.getText(R.styleable.CustomOptionView_num_option)
        tvNum.text = num

        // 내용: CustomOptionView 이름으로 만든 attrs.xml 속성중 text_option 참조
        val text = typedArray.getText(R.styleable.CustomOptionView_text_option)
        tvText.text = text

        // 옵션1: CustomOptionView 이름으로 만든 attrs.xml 속성중 option1_text 참조
        val option1 = typedArray.getText(R.styleable.CustomOptionView_option1_text)
        tvOption1.text = option1

        // 옵션2: CustomOptionView 이름으로 만든 attrs.xml 속성중 option1_text 참조
        val option2 = typedArray.getText(R.styleable.CustomOptionView_option2_text)
        tvOption2.text = option2

        typedArray.recycle()
    }
}