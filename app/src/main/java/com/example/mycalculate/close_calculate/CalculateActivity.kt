package com.example.mycalculate.close_calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mycalculate.databinding.ActivityCalculateBinding
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding: ActivityCalculateBinding

class CalculateActivity : AppCompatActivity() {
    // 탭 title
    private val tabElement = arrayListOf("로또", "포스", "복권", "토토")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 탭 어댑터 생성
        val tabAdapter = CalculateTabAdapter(this@CalculateActivity)
        // 프레그먼트, 탭 타이틀 넣기
        tabAdapter.addFragment(Tab1LottoFragment())      // 로또
        tabAdapter.addFragment(Tab2PosFragment())        // 포스
        tabAdapter.addFragment(Tab3LotteryFragment())    // 복권
        tabAdapter.addFragment(Tab4TotoFragment())       // 토토
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