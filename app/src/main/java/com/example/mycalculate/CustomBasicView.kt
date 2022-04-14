package com.example.mycalculate

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

// 기본 위젯(순서+내용+체크박스)
class CustomBasicView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var tvNum: TextView         // 순서
    lateinit var tvText: TextView        // 내용

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
        val view = LayoutInflater.from(context).inflate(R.layout.custom_basic_view,this,false)
        addView(view)
        tvNum = view.findViewById(R.id.tvNum)
        tvText = view.findViewById(R.id.tvText)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBasicView)
        setTypeArray(typedArray)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray) {
        // 순서: CustomBasicView 이름으로 만든 attrs.xml 속성중 num 참조
        val num = typedArray.getText(R.styleable.CustomBasicView_num)
        tvNum.text = num

        // 내용: CustomBasicView 이름으로 만든 attrs.xml 속성중 text 참조
        val text = typedArray.getText(R.styleable.CustomBasicView_text)
        tvText.text = text

        typedArray.recycle()
    }
}