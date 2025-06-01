package com.example.navigation_drawer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.navigation_drawer.databinding.ActivityMainBinding
import com.example.navigation_drawer.fragment.AboutFragment
import com.example.navigation_drawer.fragment.HomeFragment
import com.example.navigation_drawer.fragment.SettingFragment
import com.example.navigation_drawer.fragment.ShareFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set support action bar
        setSupportActionBar(binding.toolBar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        binding.navView.setNavigationItemSelectedListener(this)

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawLayout,
            binding.toolBar,
            R.string.open_nav,
            R.string.close_nav
        )
        binding.drawLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.navView.setCheckedItem(R.id.nav_home)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())
            R.id.nav_info -> replaceFragment(AboutFragment())
            R.id.nav_share -> replaceFragment(ShareFragment())
            R.id.nav_setting -> replaceFragment(SettingFragment())
            R.id.nav_logout -> Toast.makeText(this, "Log our", Toast.LENGTH_SHORT).show()
        }
        binding.drawLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.drawLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}