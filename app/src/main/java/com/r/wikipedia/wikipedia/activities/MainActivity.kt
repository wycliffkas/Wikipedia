package com.r.wikipedia.wikipedia.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.r.wikipedia.R
import com.r.wikipedia.wikipedia.fragments.ExplorerFragment
import com.r.wikipedia.wikipedia.fragments.FavoriteFragment
import com.r.wikipedia.wikipedia.fragments.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val favoriteFragment: FavoriteFragment
    private val historyFragment: HistoryFragment
    private val explorerFragment: ExplorerFragment

    init {
        explorerFragment = ExplorerFragment()
        favoriteFragment = FavoriteFragment()
        historyFragment =   HistoryFragment()

    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when(item.itemId){
            R.id.navigation_explore -> transaction.replace(R.id.fragment_container, explorerFragment)
            R.id.navigation_favourite -> transaction.replace(R.id.fragment_container, favoriteFragment)
            R.id.navigation_history -> transaction.replace(R.id.fragment_container, historyFragment)
        }

        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, explorerFragment)
        transaction.commit()



    }

}
