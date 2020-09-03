package br.com.dev.aboutmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.dev.aboutmovies.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager_main.adapter = ViewPageMainAdapter(baseContext, supportFragmentManager)
        tabLayout_pages.setupWithViewPager(viewPager_main)
    }
}