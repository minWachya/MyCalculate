package com.example.mycalculate.custom

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mycalculate.R

// EditText 위젯(순서+내용+EditText+체크박스)
class CustomEditView : ConstraintLayout {
    // 커스텀 뷰 안에 들어가는 아이템
    lateinit var tvNum: TextView         // 순서
    lateinit var tvText: TextView        // 내용
    lateinit var edtText: EditText       // EditText
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
        val view = LayoutInflater.from(context).inflate(R.layout.custom_edit_view,this,false)
        addView(view)
        tvNum = view.findViewById(R.id.tvNum)
        tvText = view.findViewById(R.id.tvText)
        edtText = view.findViewById(R.id.edtText)
        checkBox = view.findViewById(R.id.checkBox)
    }

    // 속성 가져오기
    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditView)
        setTypeArray(typedArray, attrs)
    }

    // 속성 사용하기
    private fun setTypeArray(typedArray : TypedArray, attrs: AttributeSet?) {
        // 순서: CustomEditView 이름으로 만든 attrs.xml 속성중 num_edit 참조
        val num = typedArray.getText(R.styleable.CustomEditView_num_edit)
        tvNum.text = num

        // 내용: CustomEditView 이름으로 만든 attrs.xml 속성중 text_edit 참조
        val text = typedArray.getText(R.styleable.CustomEditView_text_edit)
        tvText.text = text

        // 체크박스 색: CustomEditView 이름으로 만든 attrs.xml 속성중 btnTInt_edt 참조
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditView)
        val btnTIntColor = a.getColor(R.styleable.CustomEditView_btnTInt_edt, 0xFFBB86FC.toInt())
        checkBox.buttonTintList = ColorStateList.valueOf(btnTIntColor)

        typedArray.recycle()
        a.recycle()
    }

    fun getEditText(): String = edtText.text.toString()
}