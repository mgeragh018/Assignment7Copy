package edu.temple.browsr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlViewModel = ViewModelProvider(this)[UrlViewModel::class.java]
        val currentFragment = supportFragmentManager.findFragmentById(R.id.ContainerView1)

        if (savedInstanceState == null) {
            val pageFragment = PageFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.ContainerView1, pageFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
        }
        // Observe URL changes
        urlViewModel.getUrl().observe(this) {
            if (!urlViewModel.hasSeenSelection) {
                val pageFragment = PageFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.ContainerView1, pageFragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()
                urlViewModel.hasSeenSelection = true
            }
        }
    }
}