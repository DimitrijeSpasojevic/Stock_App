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
import org.koin.core.KoinApplication.Companion.init
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentProfileBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel

class FragmentAccount : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!
    private val sharedPref by inject<SharedPreferences>()
    private val prefKeyName = "prefKeyName"
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        sharedPref.getString(prefKeyName, null)?.let { mainViewModel.getUserByUserName(it) }
        binding.btnLogout.setOnClickListener{
            sharedPref
                .edit()
                .putString(prefKeyName, null)
                .apply()
            loadFragment(LoginFragment())
        }
        mainViewModel.usersState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
    }
    private fun renderState(state: UsersState) {
        when(state) {
            is UsersState.Success -> {
                    binding.userName.text = state.user.username
                    binding.email.text = state.user.email
            }
            is UsersState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT).show()
            is UsersState.DataFetched-> Toast.makeText(context, "DataFetched happened", Toast.LENGTH_SHORT).show()
            is UsersState.Loading -> Toast.makeText(context, "Loading happened", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.commit()
    }

}