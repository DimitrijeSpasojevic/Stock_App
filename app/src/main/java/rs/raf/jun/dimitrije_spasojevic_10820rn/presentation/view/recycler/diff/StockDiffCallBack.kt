package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.News
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote

class StockDiffCallBack : DiffUtil.ItemCallback<Quote>() {


    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.symbol == newItem.symbol &&
                oldItem.instrumentId == newItem.instrumentId

    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.symbol == newItem.symbol &&
                oldItem.instrumentId == newItem.instrumentId
    }

}