package br.com.dev.aboutmovies.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dev.aboutmovies.R
import br.com.dev.aboutmovies.api.MovieReviewService
import br.com.dev.aboutmovies.models.ResultReviews
import br.com.dev.aboutmovies.models.Review
import br.com.dev.sobrefilmes.utilities.API_KEY
import br.com.dev.sobrefilmes.utilities.Page
import kotlinx.android.synthetic.main.fragment_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PAGE = "page"

class PageFragment : Fragment() {
    lateinit var vm: MovieReviewViewModel
    lateinit var adapter: RecyclerReviewAdapter
    private var page: Int = Page.ALL_ITEMS.ordinal
    private var text_search: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_PAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider.NewInstanceFactory().create(MovieReviewViewModel::class.java)

        sutupRecyclerView()

        if (page == Page.SEARCH.ordinal) {
            linearLayout_search.visibility = View.VISIBLE
        } else {
            linearLayout_search.visibility = View.GONE
        }

        imageButton.setOnClickListener {
            text_search = editText_search.text.toString()
            getReview()
        }

        getReview()
    }

    fun getReview() {
        vm.getReviews(requireContext(), page, text_search, 0, local = true).observe(this, Observer {
            if (page == Page.SEARCH.ordinal) {
                adapter.setItens(it)
            } else {
                adapter.addItens(it)
            }
        })

        vm.getReviews(requireContext(), page, text_search, 0, local = false).observe(this, Observer {
            if (page == Page.SEARCH.ordinal) {
                adapter.setItens(it)
            } else {
                adapter.addItens(it)
            }
        })
    }

    fun sutupRecyclerView() {
        val itemClick = object: RecyclerReviewAdapter.OnItemClickListener {
            override fun onClick(item: Review) {
                item.link?.let {
                    if (it.url.isNotBlank()) {
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            setData(Uri.parse(it.url))
                        }
                        startActivity(intent)
                    }
                }
            }
        }

        val favoriteClick = object: RecyclerReviewAdapter.OnFavoriteClickListener {
            override fun onClick(item: Review) {
                vm.updateReview(requireContext(), item.apply {
                    favorite = !favorite
                })
            }
        }

        val shareClick = object: RecyclerReviewAdapter.OnShareClickListener {
            override fun onClick(item: Review) {
                item.link?.let {
                    if (it.url.isNotBlank()) {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, it.url)
                            type = "text/plain"
                        }
                        startActivity(shareIntent)
                    }
                }
            }
        }

        adapter = RecyclerReviewAdapter(itemClick, favoriteClick, shareClick)

        recyclerView_main.apply {
            adapter = this@PageFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(page: Page): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PAGE, page.ordinal)
                }
            }
        }
    }
}