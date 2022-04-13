package com.example.mycalculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycalculate.databinding.ActivityCloseBinding

private lateinit var binding: ActivityCloseBinding

class CloseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCloseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 리사이클러뷰 매니저 설정
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.layoutManager = layoutManager
        // 리사이클러뷰에 어댑터 달기
        val closeAdapter = CloseAdapter()
        binding.recyclerView.adapter = closeAdapter
        // divider 추가
        binding.recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        // 마감 리스트 추가
        closeAdapter.arrCloseList.add(CloseList("1.", "금고에 돈봉투, 시제 보관"))
        closeAdapter.arrCloseList.add(CloseList("2.", "난방(에어컨, 선풍기) 끄기"))
        closeAdapter.arrCloseList.add(CloseList("3.", "컴퓨터 끄기"))
        closeAdapter.arrCloseList.add(CloseList("4.", "뒷문 잠그기, 확인"))
        closeAdapter.arrCloseList.add(CloseList("5.", "냉장고 불끄기"))
        closeAdapter.arrCloseList.add(CloseList("6.", "매장, 간판 불끄기"))
        closeAdapter.arrCloseList.add(CloseList("7.", "포스기들 전원 끄기"))
        closeAdapter.arrCloseList.add(CloseList("8.", "덮개 덮기"))
        closeAdapter.arrCloseList.add(CloseList("9.", "담배 진열대 불끄기"))
        closeAdapter.arrCloseList.add(CloseList("10.", "세콤 작동"))
        closeAdapter.arrCloseList.add(CloseList("11.", "앞문 잠그기, 확인"))
        closeAdapter.notifyDataSetChanged()
    }
}