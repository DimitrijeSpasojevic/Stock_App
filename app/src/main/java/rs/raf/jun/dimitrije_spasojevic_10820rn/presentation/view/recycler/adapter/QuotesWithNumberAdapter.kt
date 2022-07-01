package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.QuoteForPortFolio
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemStockBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemStockWithNumberBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.consumer.ClickConsumer
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.consumer.ClickConsumerQuote
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.consumer.ClickConsumerQuoteForPortfolio
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff.StockDiffCallBack
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff.StockWithNumberDiffCallBack
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.viewholder.StockViewHolder
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.viewholder.StockWithNumberViewHolder

class QuotesWithNumberAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<QuoteForPortFolio, StockWithNumberViewHolder>(StockWithNumberDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockWithNumberViewHolder {
        val itemBinding = LayoutItemStockWithNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockWithNumberViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StockWithNumberViewHolder, position: Int) {
        val quote = getItem(position)
        holder.cardStock.setOnClickListener{
            onClickListener.onClick(ClickConsumerQuoteForPortfolio(quote))
        }
        holder.bind(quote)
    }

    class OnClickListener(val clickListener: (click: ClickConsumerQuoteForPortfolio) -> Unit) {
        fun onClick(click: ClickConsumerQuoteForPortfolio) = clickListener(click)
    }

}