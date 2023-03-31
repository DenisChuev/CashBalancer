package dc.cashbalancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dc.cashbalancer.databinding.ActivityMainBinding
import dc.cashbalancer.view.cards.CardsFragment
import dc.cashbalancer.view.history.HistoryFragment

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(CardsFragment())
        binding.navigation.setOnItemSelectedListener {
            if (it.itemId == R.id.home_tab) {
                replaceFragment(CardsFragment())
            } else if (it.itemId == R.id.history_tab) {
                replaceFragment(HistoryFragment())
            } else if (it.itemId == R.id.profile_tab) {
                replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.card_fragment_layout, fragment).commit()
    }
}