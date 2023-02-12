package dc.cashbalancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dc.cashbalancer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(MainFragment())
        binding.navigation.setOnItemSelectedListener {
            if (it.itemId == R.id.home_tab) {
                replaceFragment(MainFragment())
            } else if (it.itemId == R.id.profile_tab) {
                replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment).commit()
    }
}