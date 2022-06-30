package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemStockBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.consumer.ClickConsumer
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.consumer.ClickConsumerQuote
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff.StockDiffCallBack
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.viewholder.StockViewHolder

class QuotesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Quote, StockViewHolder>(StockDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemBinding = LayoutItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val quote = getItem(position)
        holder.cardStock.setOnClickListener{
            onClickListener.onClick(ClickConsumerQuote(quote))
        }
        holder.bind(quote)
    }

    class OnClickListener(val clickListener: (click: ClickConsumerQuote) -> Unit) {
        fun onClick(click: ClickConsumerQuote) = clickListener(click)
    }

}