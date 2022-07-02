package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.dimitrije_spasojevic_10820rn.R
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.User
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments.LoginFragment
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.fragments.MainFragment
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val prefKeyName = "prefKeyName"
    private val mainViewModel by viewModel<MainViewModel>()

    var name: String? = null

    private val sharedPref by inject<SharedPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            checkIfUserLog()
            false
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerMainActivity,fragment)
        transaction.commit()
    }

    private fun checkIfUserLog(){
        mainViewModel.addUser(User("d","d","123123"))
        name = sharedPref.getString(prefKeyName, null)
        if(name == null) {
            loadFragment(LoginFragment())
        }else{
            loadFragment(MainFragment())
        }
    }
}