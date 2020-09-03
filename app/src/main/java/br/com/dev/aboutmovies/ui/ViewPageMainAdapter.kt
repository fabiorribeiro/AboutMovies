package br.com.dev.aboutmovies.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.dev.aboutmovies.R
import br.com.dev.sobrefilmes.utilities.Page

class ViewPageMainAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return PageFragment.newInstance(Page.values()[position])
    }

    override fun getCount(): Int {
        return Page.values().count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getStringArray(R.array.page_title)[position]
    }
}
