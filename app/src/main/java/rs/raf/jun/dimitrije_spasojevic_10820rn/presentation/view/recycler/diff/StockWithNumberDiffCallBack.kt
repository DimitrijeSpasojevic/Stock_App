package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.News
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.QuoteForPortFolio

class StockWithNumberDiffCallBack : DiffUtil.ItemCallback<QuoteForPortFolio>() {


    override fun areItemsTheSame(oldItem: QuoteForPortFolio, newItem: QuoteForPortFolio): Boolean {
        return oldItem.symbol == newItem.symbol

    }

    override fun areContentsTheSame(oldItem: QuoteForPortFolio, newItem: QuoteForPortFolio): Boolean {
        return oldItem.symbol == newItem.symbol

    }

}