package com.example.snakeeye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(
    private val items: List<OnboardingItem>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<OnboardingAdapter.VH>() {

    inner class VH(v: View): RecyclerView.ViewHolder(v) {
        val icon: ImageView = v.findViewById(R.id.ivIcon)
        val title: TextView = v.findViewById(R.id.tvTitle)
        val desc: TextView = v.findViewById(R.id.tvDesc)
        val btn: Button = v.findViewById(R.id.btnNext)
        val dot1: View = v.findViewById(R.id.dot1)
        val dot2: View = v.findViewById(R.id.dot2)
        val dot3: View = v.findViewById(R.id.dot3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(h: VH, position: Int) {
        val item = items[position]
        h.icon.setImageResource(item.iconRes)
        h.title.text = item.title
        h.desc.text = item.desc
        h.btn.text = item.cta
        h.btn.setOnClickListener { onClick(position) }

        // dots just below the icon: highlight by page position
        h.dot1.setBackgroundResource(if (position == 0) R.drawable.dot_active else R.drawable.dot_inactive)
        h.dot2.setBackgroundResource(if (position == 1) R.drawable.dot_active else R.drawable.dot_inactive)
        h.dot3.setBackgroundResource(if (position == 2) R.drawable.dot_active else R.drawable.dot_inactive)
    }

    override fun getItemCount() = items.size
}
