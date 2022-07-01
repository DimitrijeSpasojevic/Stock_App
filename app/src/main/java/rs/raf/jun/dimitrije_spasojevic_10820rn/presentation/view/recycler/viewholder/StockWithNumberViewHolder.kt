package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.viewholder

import android.R
import android.graphics.Color
import androidx.core.graphics.green
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Bar
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.News
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.QuoteForPortFolio
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemNewsBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemStockBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemStockWithNumberBinding
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class StockWithNumberViewHolder(private val itemBinding: LayoutItemStockWithNumberBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    private  val STOCK_LABEL = "Stock chart"

    var cardStock = itemBinding.cardStock
    var lineChart = itemBinding.chart

    fun bind(quote: QuoteForPortFolio) {
        with(itemBinding){
            stockSymbol.text = quote.symbol
        }
    }


}