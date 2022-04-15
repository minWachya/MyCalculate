package com.example.mycalculate.rotate_calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mycalculate.databinding.ActivityRotateCalculateBinding
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding: ActivityRotateCalculateBinding

// 교대 정산 회면
class RotateCalculateActivity : AppCompatActivity() {
    // 탭 title
    private val tabElement = arrayListOf("로또", "포스", "복권", "토토")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRotateCalculateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 탭 어댑터 생성
        val tabAdapter = CalculateTabAdapter(this@RotateCalculateActivity)
        // 프레그먼트, 탭 타이틀 넣기
        tabAdapter.addFragment(Tab1LottoRotateFragment())      // 로또
        tabAdapter.addFragment(Tab2PosRotateFragment())        // 포스
        tabAdapter.addFragment(Tab3LotteryRotateFragment())    // 복권
        tabAdapter.addFragment(Tab4TotoRotateFragment())       // 토토
        binding.tabViewPager.adapter = tabAdapter
        // 탭레이아웃에 뷰 페이저 달기
        TabLayoutMediator(binding.tabTabLayout, binding.tabViewPager) { tab, position ->
            tab.text = tabElement[position]
        }.attach()
    }

    // 텝 어댑터
    inner class CalculateTabAdapter(activity: AppCompatActivity) :  FragmentStateAdapter(activity)  {
        // 프레그먼트 배열
        private val fragmentList = ArrayList<Fragment>()
        // 프레그먼트, 탭 타이틀 추가
        fun addFragment(fragment: Fragment) = fragmentList.add(fragment)
        // 총 프레그먼트 갯수 반환
        override fun getItemCount(): Int = fragmentList.size
        // position 번째 프레그먼트 반환
        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}