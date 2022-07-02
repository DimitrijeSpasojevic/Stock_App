package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.NewsResponseData
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.QuotesResponseData
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentDiscoveryBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter.NewsAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter.QuotesAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.io.IOException


class FragmentDiscovery : Fragment(R.layout.fragment_discovery) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentDiscoveryBinding? = null
    private lateinit var adapter: NewsAdapter
    private lateinit var adapterQuotes: QuotesAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initObservers() {
        //mainViewModel.fetchAllNews()
        convert()
        convert2()
    }


    private fun initUi() {
        initRecycler()
//        initListeners()
    }

//    private fun initListeners(){
//
//    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = NewsAdapter(NewsAdapter.OnClickListener {
            val url = it.news.link
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })
        binding.listRv.adapter = adapter

        binding.listRvQuotes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterQuotes = QuotesAdapter(QuotesAdapter.OnClickListener {
            loadFragmentWithBack(FragmentDetailQuote(it.quote))
        })
        binding.listRvQuotes.adapter = adapterQuotes
    }
    fun convert() {
        val jsonString =getJsonDataFromAsset(requireContext(),"getNews.json")

        val news = Gson().fromJson(jsonString, NewsResponseData::class.java)
        Timber.d("Data object: ${news.data.newsItems}")
        val newsList  = news.data.newsItems
        adapter.submitList(newsList)
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

    fun convert2() {
        val jsonString = getJsonDataFromAsset(requireContext(),"getIndexes.json")

        val quotes = Gson().fromJson(jsonString, QuotesResponseData::class.java)
//        Timber.d("QuotesResponseData object: ${quote.data.quote}")
        val quoteList  = quotes.data.quotes
        adapterQuotes.submitList(quoteList)
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.commit()
    }

    private  fun loadFragmentWithBack(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}