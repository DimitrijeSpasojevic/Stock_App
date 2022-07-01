package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.*
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentDetailQuoteBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.io.IOException
import kotlin.text.Typography.quote

class FragmentDetailQuote : Fragment(R.layout.fragment_detail_quote) {

    private var _binding: FragmentDetailQuoteBinding? = null
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private val binding get() = _binding!!
    private  val STOCK_LABEL = "Stock chart"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        mainViewModel.getAllByUserIdAndSymbol(1,"T")
        convert()
        mainViewModel.portfolioItemState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })

        binding.btnBuy.setOnClickListener{
            loadFragmentWithBack(FragmentBuy())
        }
        binding.btnSell.setOnClickListener{
            loadFragmentWithBack(FragmentSell())
        }


    }
    private fun renderState(state: PortfolioState) {
        when(state) {
            is PortfolioState.Success -> {
                if(state.portfolioItems.isEmpty()){
                    binding.btnSell.visibility = View.INVISIBLE
                }

            }
            is PortfolioState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
            is PortfolioState.DataFetched-> Toast.makeText(context, "DataFetched happened", Toast.LENGTH_SHORT).show()
            is PortfolioState.Loading -> Toast.makeText(context, "Loading happened", Toast.LENGTH_SHORT).show()
        }
    }


    fun convert() {
        val jsonString =getJsonDataFromAsset(requireContext(),"searchQuote_symbol=T.json")

        val quote = Gson().fromJson(jsonString, SearchQuotesResponseData::class.java)
        Timber.d("Data object: ${quote.data}")
        var searchQuote = quote.data
        with(binding){
            symbol.text = "Symbol: " + searchQuote.symbol
            price.text = "Price: " + searchQuote.last.toString() + " - " + searchQuote.currency
            open.text = "open: " + searchQuote.open.toString()
            bid.text = "bid: " + searchQuote.bid.toString()
            close.text = "close: " + searchQuote.close.toString()
            ask.text = "ask: " + searchQuote.ask.toString()
            eps.text = "eps: " + searchQuote.metrics.eps.toString()
            ebit.text = "ebit: " + searchQuote.metrics.ebit.toString()
            beta.text = "beta: " + searchQuote.metrics.beta.toString()
        }

        setLineChartData(searchQuote)
    }

    fun setLineChartData(quote: SearchQuote){
        val bars = quote.chart.bars
        val dayData = mutableListOf<Entry>()

        var i = 0f
        bars.forEach {
            dayData.add(Entry(i, it.price.toFloat()))
            i++
        }

        val lineDataSet = LineDataSet(dayData,STOCK_LABEL)
//        if(quote.changeFromPreviousClose < 0){
//            lineDataSet.setColor(Color.RED)
//        }else{
//            lineDataSet.setColor(Color.GREEN)
//        }
        val lineData: LineData = LineData(lineDataSet)

        binding.chartDetail.description.isEnabled = false
        binding.chartDetail.data = lineData
        binding.chartDetail.invalidate()


    }


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private  fun loadFragmentWithBack(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}