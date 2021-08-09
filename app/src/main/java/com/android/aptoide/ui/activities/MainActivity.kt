package com.android.aptoide.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.android.aptoide.R
import com.android.aptoide.databinding.ActivityMainBinding
import com.android.aptoide.network.repos.AppsListRepository
import com.android.aptoide.ui.bindingadapters.ISwipableRefresh
import com.android.utils.isNetworkAvailable
import com.android.utils.setStatusBarDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repo: AppsListRepository

    private val navHostFragment get() = supportFragmentManager.fragments.firstOrNull { it is NavHostFragment }

    private val hasInternetConnection get() = isNetworkAvailable(baseContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Making the status bar - toolbar transition smooth
        setStatusBarDrawable(this, R.drawable.header_gradient)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation(binding.bottomNavigation)

        RxJavaPlugins.setErrorHandler { Log.e(TAG, "Error ${it.message}") }

        // Set on swipe down behaviour
        setOnSwipeBehaviour()

        // Assess internet connection
        updateInternetStatus()

        // Set click listeners
        binding.profileIv.setOnClickListener { showToast("Pressed profile") }
    }

    private fun setOnSwipeBehaviour() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            updateInternetStatus()
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
                R.id.home -> showToast("Pressed home")
                R.id.search -> showToast("Pressed search")
                R.id.stores -> showToast("Pressed stores")
                R.id.apps -> showToast("Pressed apps")
                else -> false
            }
        }
    }

    fun changeProgressBarState(enable: Boolean) {
        binding.progressBar.visibility = if (enable) VISIBLE else GONE
    }

    fun resetSwipe() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    /**
     * Show or Hide no internet connection layout
     */
    private fun updateInternetStatus() {
        binding.noInternetLl.visibility = if (hasInternetConnection) GONE else VISIBLE
    }

    // For development/sample viewing purposes
    private fun showToast(msg: String): Boolean {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        return true
    }
}
