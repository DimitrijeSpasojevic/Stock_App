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
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioStateUpdate
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel
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
        var userId = sharedPref.getLong(prefUserId, -1);
        sharedPref.getString(prefKeyName, null)?.let { mainViewModel.getUserByUserName(it) }
        binding.btnSellStock.setOnClickListener{
            mainViewModel.deleteByUserIdAndSym(userId ,selectedQuote.symbol)
        }

        mainViewModel.portfolioUpdateItemState.observe(viewLifecycleOwner, Observer {
            renderState(it , username,port,acc)
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

    private fun renderState(state: PortfolioStateUpdate, username:String, port:Double ,acc: Double) {
        when(state) {
            is PortfolioStateUpdate.Success -> {
                Toast.makeText(context, "Prodato", Toast.LENGTH_SHORT).show()
                mainViewModel.updateUser(UserUpdateDto(username,acc + selectedQuote.last,port - selectedQuote.last))
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