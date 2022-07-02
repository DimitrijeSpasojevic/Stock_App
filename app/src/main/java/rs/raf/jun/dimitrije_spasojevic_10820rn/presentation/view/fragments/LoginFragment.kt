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
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.FragmentLoginBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.UsersState
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val prefKeyName = "prefKeyName"
    private val prefUserId = "id"
    private val binding get() = _binding!!
    private val sharedPref by inject<SharedPreferences>()
    private val password = "123123"
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObserver()
    }

    private fun initObserver() {
        mainViewModel.usersState.observe(viewLifecycleOwner, Observer{
            if(it is UsersState.Success){
                it.user.id?.let { it1 ->
                    sharedPref
                        .edit()
                        .putLong(prefUserId, it1)
                        .apply()
                }
                if(sharedPref.getString(prefKeyName,null) != null)
                    loadFragment(MainFragment())
            }
        })
    }

    private fun init() {
        var pass = binding.text3
        var username = binding.text1
        var email = binding.text2


        binding.b1.setOnClickListener{
            if(pass.text.isEmpty() || username.text.isEmpty() || email.text.isEmpty()){
                Toast.makeText(requireContext(),"Sva polja moraju biti popunjena",Toast.LENGTH_SHORT).show();
            }
            else if(pass.text.length < 5){
                Toast.makeText(requireContext(),"Sifra mora biti 5 karaktera",Toast.LENGTH_SHORT).show();
            }else{
                if(pass.text.toString() == password && username.text.toString() == "d"){
                    sharedPref
                        .edit()
                        .putString(prefKeyName, username.text.toString())
                        .apply()

                    mainViewModel.getUserByUserName(username.text.toString())

                }else{
                    Toast.makeText(requireContext(),"Pogresna sifra",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.commit()
    }

}