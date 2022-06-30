package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments

import android.os.Bundle
import android.view.View

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import rs.raf.jun.dimitrije_spasojevic_10820rn.R


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var bottomNav : BottomNavigationView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFragment(FragmentDiscovery())
        bottomNav = requireActivity().findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.discovery -> loadFragment(FragmentDiscovery())
                R.id.portfolio -> loadFragment(FragmentPortfolio())
                R.id.account -> loadFragment(FragmentAccount())
            }
            true
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(rs.raf.jun.dimitrije_spasojevic_10820rn.R.id.fragmentContainer,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}