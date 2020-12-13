package com.stackapp.view.adapter

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.stackapp.R
import com.stackapp.databinding.ItemRepayPlanBinding
import com.stackapp.model.RepayModel


class RepayPlanAdapter :
    RecyclerView.Adapter<RepayPlanAdapter.TestsViewHolder>() {

    private var list = ArrayList<RepayModel>()
    var onItemClick: ((RepayModel, Int) -> Unit)? = null
    var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsViewHolder {
        val binding: ItemRepayPlanBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_repay_plan, parent, false
        )
        return TestsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (list.size > 0) list.size else 0
    }

    override fun onBindViewHolder(holder: TestsViewHolder, position: Int) {
        holder.bind(list[position])

        if (position == selectedItem) {
            holder.binding.selector.background = holder.itemView.resources.getDrawable(R.drawable.repay_option_selected)
            holder.binding.selectPlan.visibility = View.VISIBLE
        } else {
            holder.binding.selector.background = holder.itemView.resources.getDrawable(R.drawable.repay_option_unselected)
            holder.binding.selectPlan.visibility = View.GONE
        }
    }

    private fun getbackground(background: Int, itemView: View) : Int{
        return when (background) {
            itemView.resources.getColor(R.color.color1) -> itemView.resources.getColor(R.color.color1Selected)
            itemView.resources.getColor(R.color.color2) -> itemView.resources.getColor(R.color.color2Selected)
            itemView.resources.getColor(R.color.color3) -> itemView.resources.getColor(R.color.color3Selected)
            else -> 0
        }
    }

    fun setData(list: ArrayList<RepayModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun updateSelectedPosition(selected: Int) {
        val previousItem = selectedItem
        selectedItem = selected

        notifyItemChanged(previousItem)
        notifyItemChanged(selected)
    }

    inner class TestsViewHolder(var binding: ItemRepayPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepayModel) {
            binding.executePendingBindings()

            binding.selector.background = itemView.resources.getDrawable(R.drawable.repay_option_unselected)
            binding.selectPlan.visibility = View.GONE

            binding.card.setCardBackgroundColor(item.background)
            val string =
                SpannableString(itemView.resources.getString(R.string.Rs) + item.amount + " /mo")
            string.setSpan(RelativeSizeSpan(1.3f), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            string.setSpan(
                ForegroundColorSpan(getColorForCalculations(item.background)),
                string.indexOf("/"), string.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.planAmount.text = string

            binding.planDuration.text = "for " + item.duration + " months"
            binding.planDuration.setTextColor(getColorForDurations(item.background))
            binding.txtSeeCalculations.setTextColor(getColorForCalculations(item.background))

//            (binding.txtSeeCalculations.background as GradientDrawable).setStroke(2, getColorForCalculations(item.background))
        }

        init {
            binding.selector.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition], adapterPosition)
            }
        }

        private fun getColorForCalculations(background: Int): Int {
            return when (background) {
                itemView.resources.getColor(R.color.color1) -> itemView.resources.getColor(R.color.color1Calculations)
                itemView.resources.getColor(R.color.color2) -> itemView.resources.getColor(R.color.color2Calculations)
                itemView.resources.getColor(R.color.color3) -> itemView.resources.getColor(R.color.color3Calculations)
                else -> 0
            }
        }

        private fun getColorForDurations(background: Int) : Int {
            return when (background) {
                itemView.resources.getColor(R.color.color1) -> itemView.resources.getColor(R.color.color1Durations)
                itemView.resources.getColor(R.color.color2) -> itemView.resources.getColor(R.color.color2Durations)
                itemView.resources.getColor(R.color.color3) -> itemView.resources.getColor(R.color.color3Durations)
                else -> 0
            }
        }
    }
}