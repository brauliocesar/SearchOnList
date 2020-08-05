package br.com.itbam.searchonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.itbam.searchonlist.databinding.LayoutNameViewBinding

class NameListAdapter(var dataList: Array<String> = mutableListOf<String>().toTypedArray())
    : RecyclerView.Adapter<NameListAdapter.NameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = LayoutNameViewBinding.inflate(inflater)

        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.binding.txvName.text = dataList.get(position)
    }

    class NameViewHolder(val binding: LayoutNameViewBinding)
        : RecyclerView.ViewHolder(binding.root)
}
