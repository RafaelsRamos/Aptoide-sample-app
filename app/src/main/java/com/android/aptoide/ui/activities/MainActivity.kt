package com.android.aptoide.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.android.aptoide.R
import com.android.aptoide.databinding.ActivityMainBinding
import com.android.aptoide.network.repos.AppsListRepository
import com.android.aptoide.ui.bindingadapters.ISwipableRefresh
import com.android.utils.setStatusBarDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repo: AppsListRepository

    val navHostFragment get() = supportFragmentManager.fragments.firstOrNull { it is NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Making the status bar - toolbar transition smooth
        setStatusBarDrawable(this, R.drawable.header_gradient)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation(binding.bottomNavigation)

        RxJavaPlugins.setErrorHandler { Log.e("MainActivity", "Error ${it.message}") }

        // Set on swipe down behaviour
        setOnSwipeBehaviour()
    }

    private fun setOnSwipeBehaviour() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            navHostFragment?.let {
                // Run through fragments in stack and want them the SwipeRefreshLayout was swiped
                for (frag in it.childFragmentManager.fragments) {
                    if (frag is ISwipableRefresh) {
                        frag.onSwiped()
                    }
                }
            }
        }
    }

    private fun setBottomNavigation(bnView: BottomNavigationView) {
        bnView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(baseContext, "Pressed home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.search -> {
                    Toast.makeText(baseContext, "Pressed search", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.stores -> {
                    Toast.makeText(baseContext, "Pressed stores", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.apps -> {
                    Toast.makeText(baseContext, "Pressed apps", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    fun changeProgressBarState(enable: Boolean) {
        binding.progressBar.visibility = if (enable) View.VISIBLE else View.GONE
    }

    fun resetSwipe() {
        binding.swipeRefreshLayout.isRefreshing = false
    }
}
