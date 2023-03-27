package com.easy_pro_code.wallet.HomeFlow.Presentation.myTransaction

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.easy_pro_code.wallet.HomeFlow.model.MyTransaction
import com.easy_pro_code.wallet.databinding.MyTransactionItemBinding

class TransactionAdapter(val transactionList: List<MyTransaction>) : androidx.recyclerview.widget.ListAdapter<MyTransaction, RecyclerView.ViewHolder>(MyTransactionDiffUtil())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyTransactionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item =getItem(position)
        (holder as MyTransactionViewHolder).bind(item)
    }

}

class MyTransactionViewHolder(val binding:MyTransactionItemBinding):RecyclerView.ViewHolder(binding.root){
    companion object{
        fun create(parent: ViewGroup):MyTransactionViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val binding = MyTransactionItemBinding.inflate(inflater, parent, false)
            return MyTransactionViewHolder(binding)
        }
    }

    fun bind(myTransaction:MyTransaction){
        binding.transImage.setImageResource(myTransaction.image.toInt())
        binding.title.text = myTransaction.title
        binding.fromOrTo.text = myTransaction.status
        binding.date.text = myTransaction.date
        binding.transMoney.text = myTransaction.money
        binding.phoneNumber.text = myTransaction.phone
    }
}


class MyTransactionDiffUtil : DiffUtil.ItemCallback<MyTransaction>()
{
    override fun areItemsTheSame(oldItem: MyTransaction, newItem: MyTransaction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MyTransaction, newItem: MyTransaction): Boolean
    {
        return oldItem.id == newItem.id
    }

}