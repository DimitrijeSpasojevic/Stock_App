package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserFull
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.UserUpdateDto
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentSellBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.PortfolioStateUpdate
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel

class FragmentSell : Fragment(R.layout.fragment_sell) {

    private var _binding: FragmentSellBinding? = null

    private val binding get() = _binding!!
    private val sharedPref by inject<SharedPreferences>()
    private val prefKeyName = "prefKeyName"
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private lateinit var loggedUser: UserFull

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSellBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        sharedPref.getString(prefKeyName, null)?.let { mainViewModel.getUserByUserName(it) }
        binding.btnSellStock.setOnClickListener{
            mainViewModel.deleteByUserIdAndSym(1,"T")
            mainViewModel.updateUser(UserUpdateDto(loggedUser.username,loggedUser.accountValue + 1000, loggedUser.portfolioValue - 10000))

        }

        mainViewModel.portfolioUpdateItemState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        mainViewModel.usersState.observe(viewLifecycleOwner, Observer {
            if(it is UsersState.Success)
                loggedUser = it.user
            renderStateUser(it)
        })
    }

    private fun renderStateUser(state: UsersState) {
        when(state) {
            is UsersState.Success -> {
                binding.accValue.text = "Stanje na racunu: " + state.user.accountValue.toString()
                Toast.makeText(context, "Prodato", Toast.LENGTH_SHORT).show()
            }
            is UsersState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderState(state: PortfolioStateUpdate) {
        when(state) {
            is PortfolioStateUpdate.Success -> {
                if(loggedUser == null){
                    sharedPref.getString(prefKeyName, null)?.let { mainViewModel.getUserByUserName(it) }
                }
                mainViewModel.updateUser(UserUpdateDto(loggedUser.username,loggedUser.accountValue + 1000, loggedUser.portfolioValue - 10000))
                Toast.makeText(context, "Kupljeno", Toast.LENGTH_SHORT).show()
                mainViewModel.getUserByUserName(loggedUser.username)
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