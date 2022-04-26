package com.example.mycalculate.custom

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.CompoundButtonCompat
import com.example.mycalculate.R


// 기본 위젯(순서+내용+체크박스)
class CustomBasicView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var tvNum: TextView         // 순서
    lateinit var tvText: TextView        // 내용
    lateinit var checkBox: CheckBox      // 체크박스

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
        checkBox = view.findViewById(R.id.checkBox)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBasicView)
        setTypeArray(typedArray, attrs)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray, attrs: AttributeSet?) {
        // 순서: CustomBasicView 이름으로 만든 attrs.xml 속성중 num_basic 참조
        val num = typedArray.getText(R.styleable.CustomBasicView_num_basic)
        tvNum.text = num

        // 내용: CustomBasicView 이름으로 만든 attrs.xml 속성중 text_basic 참조
        val text = typedArray.getText(R.styleable.CustomBasicView_text_basic)
        tvText.text = text

        // 체크박스 색: CustomBasicView 이름으로 만든 attrs.xml 속성중 btnTInt_basic 참조
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomBasicView)
        val btnTIntColor = a.getColor(R.styleable.CustomBasicView_btnTInt_basic, 0xFFBB86FC.toInt())
        checkBox.buttonTintList = ColorStateList.valueOf(btnTIntColor)

        typedArray.recycle()
        a.recycle()
    }
}