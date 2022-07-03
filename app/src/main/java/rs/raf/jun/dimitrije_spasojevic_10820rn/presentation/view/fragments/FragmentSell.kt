package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.PortfolioHistoryItem
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.Quote
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentSellBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioStateUpdate
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.sql.Date
import java.time.LocalDate

class FragmentSell(quote: Quote) : Fragment(R.layout.fragment_sell) {

    private var _binding: FragmentSellBinding? = null

    private val binding get() = _binding!!
    private val sharedPref by inject<SharedPreferences>()
    private val prefKeyName = "prefKeyName"
    private val prefUserId = "id"
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private val selectedQuote = quote
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSellBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init() {
        var acc:Double = 0.0
        var port:Double = 0.0
        var username = ""
        var numberOfStocksToSell = 0
        var totalNumberOfStockWithSym = 0
        var userId = sharedPref.getLong(prefUserId, -1);
        sharedPref.getString(prefKeyName, null)?.let { mainViewModel.getUserByUserName(it) }
        mainViewModel.portfolioItemState.observe(viewLifecycleOwner, Observer{
            if (it is PortfolioState.Success){
                totalNumberOfStockWithSym = it.portfolioItems.size
                binding.numOfStock.text = "Trenutno imate " + totalNumberOfStockWithSym + " deonica " + selectedQuote.symbol
            }
        })
        binding.stockName.text = "Stock name " + selectedQuote.name
        binding.tglTitle.text = "Sell all stocks with symbol - " + selectedQuote.symbol
        binding.tglSellAll.setOnClickListener{
            if(binding.tglSellAll.isChecked){
                binding.inputStockNumber.visibility = View.INVISIBLE
                mainViewModel.deleteAllByUserIdAndSym(userId,selectedQuote.symbol)
                Toast.makeText(context, "Prodato " + totalNumberOfStockWithSym  + " deonica sa simbolom " + selectedQuote.symbol, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSellStock.setOnClickListener{
            numberOfStocksToSell = binding.inputStockNumber.text.toString().toInt()
            if(numberOfStocksToSell > totalNumberOfStockWithSym){
                Toast.makeText(context, "Trenutno nemate toliko deonica", Toast.LENGTH_SHORT).show()
            }else{
                for (i in 0 until numberOfStocksToSell)
                    mainViewModel.deleteByUserIdAndSym(userId ,selectedQuote.symbol)
            }
        }

        mainViewModel.portfolioUpdateItemState.observe(viewLifecycleOwner, Observer {
            renderState(it ,username, port, acc, numberOfStocksToSell, totalNumberOfStockWithSym)
        })
        mainViewModel.usersState.observe(viewLifecycleOwner, Observer {
            if(it is UsersState.Success) {
                binding.accValue.text = "Stanje na racunu: " + it.user.accountValue.toInt().toString()
                acc = it.user.accountValue
                port = it.user.portfolioValue
                username = it.user.username
                mainViewModel.insertPortfolioHistoryEntity(
                    PortfolioHistoryItem(port,userId,
                    Date.valueOf(LocalDate.now().toString()))
                )
            }
        })
    }

    private fun renderState(state: PortfolioStateUpdate, username:String, port:Double ,acc: Double,numberOfStocksToSell: Int, totalNumOfStocks: Int) {
        when(state) {
            is PortfolioStateUpdate.Success -> {
                if(state.msgFromWhere == "deleteAll"){
                    Toast.makeText(context, "Prodato svih " + totalNumOfStocks + " deonica", Toast.LENGTH_SHORT).show()
                    mainViewModel.updateUser(UserUpdateDto(username,acc + (selectedQuote.last * totalNumOfStocks),port - (selectedQuote.last * totalNumOfStocks)))
                }else{
                    Toast.makeText(context, "Prodato " + numberOfStocksToSell + " deonica", Toast.LENGTH_SHORT).show()
                    mainViewModel.updateUser(UserUpdateDto(username,acc + (selectedQuote.last * numberOfStocksToSell),port - (selectedQuote.last * numberOfStocksToSell)))
                }
            }
            is PortfolioStateUpdate.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.commit()
    }

}