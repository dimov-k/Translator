package ru.mrroot.translator.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrroot.translator.databinding.ActivityMainItemBinding
import ru.mrroot.translator.model.data.DataModel

class MainAdapter(private var itemClickListener: ((DataModel) -> Unit)?) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ActivityMainItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val vb: ActivityMainItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        private lateinit var data: DataModel

        init {
            itemView.setOnClickListener { itemClickListener?.invoke(data) }
        }

        fun bind(data: DataModel) {
            this.data = data
            vb.headerTextviewRv.text = data.text
            vb.descriptionTextviewRv.text = data.meanings?.get(0)?.translation?.translation
            vb.transcriptionTextviewRv.text = data.meanings?.get(0)?.transcription
        }
    }
}