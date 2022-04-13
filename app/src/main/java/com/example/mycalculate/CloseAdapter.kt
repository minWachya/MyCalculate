package com.example.mycalculate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalculate.databinding.ListItemCloseBinding

data class CloseList(val num: String, val text: String)

private lateinit var binding: ListItemCloseBinding

class CloseAdapter : RecyclerView.Adapter<CloseAdapter.ViewHolder>() {
    val arrCloseList = ArrayList<CloseList>() // 알림 배열

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CloseAdapter.ViewHolder {
        binding = ListItemCloseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // position 번째 아이템 설정하기
    override fun onBindViewHolder(holder: CloseAdapter.ViewHolder, position: Int) =
        holder.setItem(arrCloseList[position])

    // 아이템 갯수 리턴
    override fun getItemCount() = arrCloseList.size

    // 알림 뷰 생성
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(item: CloseList) {
            binding.tvNum.text = item.num
            binding.tvText.text = item.text
        }
    }

}
