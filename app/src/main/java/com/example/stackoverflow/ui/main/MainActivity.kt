package com.example.stackoverflow.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashlytics.android.Crashlytics
import com.example.stackoverflow.Adapter.main.QuestionRecyclerAdapter
import com.example.stackoverflow.Adapter.main.onClickListener
import com.example.stackoverflow.BaseApp
import com.example.stackoverflow.R
import com.example.stackoverflow.model.Question
import com.example.stackoverflow.ui.details.DetailsActivity
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), IMainPresenter.View, onClickListener {
    override fun showDetailed(id: Long) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onClicked(id: Long) {
        presenter.showDetailed(id)
    }

    @Inject
    lateinit var presenter: IMainPresenter.Presenter

    var isLoading: Boolean = false

    var adapter: QuestionRecyclerAdapter = QuestionRecyclerAdapter(emptyList(), this, this)

    override fun bindQuestion(list: List<Question>?) {
        isLoading = false
        list?.let { adapter.updateList(list) }
        mainRecyclerView.adapter?.notifyDataSetChanged()
        mainRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !isLoading) { //1 down,-1 up,0 will always return false
                    presenter.getMoreQuestions()
                    isLoading = true
                }
            }
        })
    }

    override fun onFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getRootView(): View? {
        return this.getRootView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fabric.with(this, Crashlytics())
        (application as BaseApp).baseComponent.inject(this)
        presenter.initView(this)
        presenter.getQuestions()
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = adapter
        mainRefreshView.setOnRefreshListener {
            presenter.getQuestions()
            mainRefreshView.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val mSearch = menu.findItem(R.id.action_search)
        val mSearchView = mSearch.actionView as SearchView
        mSearchView.queryHint = "Search"

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getQuestions(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}

