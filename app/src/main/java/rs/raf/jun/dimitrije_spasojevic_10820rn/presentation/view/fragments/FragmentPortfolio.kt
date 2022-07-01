package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.QuoteForPortFolio
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentDiscoveryBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentPortfolioBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter.NewsAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter.QuotesAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter.QuotesWithNumberAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel

class FragmentPortfolio : Fragment(R.layout.fragment_portfolio) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentPortfolioBinding? = null
    private lateinit var adapter: QuotesWithNumberAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        mainViewModel.getAllByUserId(1)
        mainViewModel.portfolioItemState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })

        binding.listRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = QuotesWithNumberAdapter(QuotesWithNumberAdapter.OnClickListener {

        })
        binding.listRv.adapter = adapter
    }

    private fun renderState(state: PortfolioState) {
        when(state) {
            is PortfolioState.Success -> {
                var quotes = state.portfolioItems.map {
                    QuoteForPortFolio(it.sym,it.userId,it.quantity)
                }
                adapter.submitList(quotes)
            }
            is PortfolioState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
            is PortfolioState.DataFetched-> Toast.makeText(context, "DataFetched happened", Toast.LENGTH_SHORT).show()
            is PortfolioState.Loading -> Toast.makeText(context, "Loading happened", Toast.LENGTH_SHORT).show()
        }
    }
}