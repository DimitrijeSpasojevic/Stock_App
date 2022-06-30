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
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemNewsBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemStockBinding
import java.security.KeyStore
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class StockViewHolder(private val itemBinding: LayoutItemStockBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    private  val STOCK_LABEL = "Stock chart"

    var cardStock = itemBinding.cardStock
    var lineChart = itemBinding.chart

    fun bind(quote: Quote) {
        with(itemBinding){
            stockName.text = quote.name
            stockSymbol.text = quote.symbol
            stockPrice.text = quote.last.toString() + " - " + quote.currency
        }
        setLineChartData(quote)
    }

    fun setLineChartData(quote: Quote){
        val bars = quote.chart.bars
        val dayData = mutableListOf<Entry>()

        var i = 0f
        bars.forEach {
            dayData.add(Entry(i, it.price.toFloat()))
            i++
        }

        val lineDataSet = LineDataSet(dayData,STOCK_LABEL)
        if(quote.changeFromPreviousClose < 0){
            lineDataSet.setColor(Color.RED)
        }else{
            lineDataSet.setColor(Color.GREEN)
        }
        val lineData: LineData = LineData(lineDataSet)

        lineChart.description.isEnabled = false
        lineChart.data = lineData
        lineChart.invalidate()


    }

}